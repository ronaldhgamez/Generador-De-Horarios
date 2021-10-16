package main;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;
import models.*;

/**
 * CLASE SINGLETON QUE CONTIENE LA INFORMACIÓN DEL PROGRAMA EN ARRAYLIST
 * CONTIENE TODOS LOS METODOS PARA EL MANEJO DE LAS LISTAS
 * 
 * IMPLEMENTA EL PATRÓN DE DISEÑO OBSERVER
 * LOS CAMBIOS QUE SE PRODUZCAN SERÁN OBSERVADOS INMEDIATAMENTE POR LA CLASE
 * OBSERVER (SQLITE) PARA SUBIR LOS DATOS A LA BASE DE DATOS
 * 
 * @author Ronaldhg
 */
public class Listas  extends Observable {
    
    public ArrayList<Aula> aulas;
    public ArrayList<Curso> cursos;
    public ArrayList<Profesor> profesores;
    public ArrayList<Imparte> imparte;
    public ArrayList<Disponible> disponible;
    public ArrayList<DisponibleA> disponibleA;
    
    /* ÚNICO OBSERVADOR */
    private Observer observer;
    /* SENTENCIA SQL A EJECUTAR CUANDO SE REALICE UNA MODIFICACIÓN */
    private String sentencia;
    /* PARA SABER SI HAY QUE VOLVER A SUBIR LOS DATOS A PROLOG EN CASO DE MODIFICACIÓN */
    public boolean fueModificado;
    
    private Listas() {
        this.aulas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.imparte = new ArrayList<>();
        this.disponible = new ArrayList<>();
        this.disponibleA = new ArrayList<>();
        this.fueModificado = false;
        /* AGREGAMOS EL OBSERVADOR A LA CLASE */
        this.observer = main.Main.sqlite;
        this.sentencia = null;
        this.fueModificado = false;
    }
    
    public static Listas getInstance() {
        return ListasHolder.INSTANCE;
    }
    
    private static class ListasHolder {
        private static final Listas INSTANCE = new Listas();
    }
    
    /**
     * NOTIFICA AL OBSERVADOR QUE ESTA CLASE HA SIDO MODIFICADA
     */
    @Override
    public void notifyObservers() {
        if(observer != null) {
            observer.update(this, sentencia);
        }
    }
    
    
    //*************************************************************************
    //******************* METODOS PARA CLASE PROFESOR *************************
    //*************************************************************************
    
    /**
     * FUNCIÓN PARA REGISTRAR PROFESORES. RETORNA TRUE SI EL REGISTRO SE INSERTA
     * Y FALSE SI NO. REALIZA VALIDACIONES PARA EVITAR CONFLICTOS CON LOS DATOS.
     * @param cedula
     * @param nombre
     * @return 
     */
    public boolean insertarProfesor(String cedula, String nombre) {
        // VALIDA QUE NO HAYA CAMPOS NULOS
        if(nombre.equals("") || cedula.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo vacio, inserte la información necesaria", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // VALIDA QUE NO SE REPITA LA CEDULA
        if(buscarProfesor(cedula) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro con esa cédula", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // INSERTA EL REGISTRO
        profesores.add(new Profesor(cedula, nombre));
        
        // NOTIFICA AL OBSERVADOR QUE SE HA CREADO UN NUEVO DATO
        sentencia = "INSERT INTO Profesor (cedula, nombre) VALUES ('"+cedula+"', '"+nombre+"')";
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Se ha guardado el registro con cédula " + cedula);
        return true;
    }
    
    /**
     * FUNCIÓN PARA MODIFICAR LOS DATOS DE PROFESORES. RETORNA TRUE SI SE MODIFICA
     * Y FALSE SI NO. REALIZA VALIDACIONES PARA EVITAR CONFLICTOS CON LOS DATOS.
     * @param nombreNuevo
     * @param cedulaVieja
     * @param cedulaNueva
     * @return 
     */
    public boolean editarProfesor(String nombreNuevo, String cedulaVieja, String cedulaNueva) {
        // VALIDA QUE NO HAYA CAMPOS NULOS
        if(nombreNuevo.equals("") || cedulaNueva.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo vacio, inserte la información necesaria!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // VALIDA QUE LA CEDULA NO SE REPITA Y SI NO SE MODIFICA LA CEDULA DEJA MODIFICAR
        if(buscarProfesor(cedulaNueva) != null && ! cedulaVieja.equals(cedulaNueva)) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro con esa cédula",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // MODIFICACIÓN DE LOS DATOS
        buscarProfesor(cedulaVieja).setNombre(nombreNuevo);
        // SI HUBO CAMBIOS EN LA CEDULA HACE EL CAMBIO EN TODOS LOS REGISTROS
        // QUE TIENEN COMO LLAVE FORANEA LA CÉDULA
        if( ! cedulaVieja.equals(cedulaNueva) ) {
            // MODIFICA EN LOS HORARIOS Y EN LA RELACIÓN CURSO-PROFESOR
            actualizarCedula(cedulaVieja, cedulaNueva);
            // MODIFICA LA CEDULA
            buscarProfesor(cedulaVieja).setCedula(cedulaNueva);
            
            // MODIFICA EN LA BD LA RELACIÓN DE PROFESORES Y CURSOS
            sentencia = "UPDATE Imparte SET cedula = '" + cedulaNueva + "' "
                    + "WHERE cedula = '" + cedulaVieja + "'";
            notifyObservers();
            // ACTUALIZA LA CEDULA EN LOS HORARIOS DEL PROFESOR EN LA BD
            sentencia = "UPDATE Disponible SET cedula = '" + cedulaNueva + "' "
                    + "WHERE cedula = '" + cedulaVieja + "'";
            notifyObservers();
            // ACTUALIZA LA CEDULA DEL PROFESOR EN LA BASE DE DATOS
            sentencia = "UPDATE Profesor SET cedula = '" + cedulaNueva + "', "
                    + "nombre = '" + nombreNuevo + "' WHERE cedula = '" + cedulaVieja + "'";
            notifyObservers();
        } else {
            sentencia = "UPDATE Profesor SET nombre = '" + nombreNuevo + "' WHERE cedula = '" + cedulaVieja + "'";
            notifyObservers();
        }
        JOptionPane.showMessageDialog(null, "Se han guardado los cambios correctamente!");
        return true;
    }
    
    /**
     * SE LLAMA AL EDITAR LA CEDULA EN UN PROFESOR. MODIFICA TODOS LOS REGISTROS
     * DE LAS DEMÁS LISTAS DONDE LA CÉDULA ES LLAVE FORÁNEA.
     * @param cedulaVieja
     * @param cedulaNueva 
     */
    private void actualizarCedula(String cedulaVieja, String cedulaNueva) {
        // MODIFICA LA CEDULA EN LA RELACIÓN PROFESOR-CURSO
        for (Imparte i : imparte)
            if(i.getCedula().equals(cedulaVieja))
                i.setCedula(cedulaNueva);
        
        // MODIFICA LA CEDULA EN LOS HORARIOS DE DISPONIBILIDAD
        for (Disponible i : disponible)
            if(i.getCedula().equals(cedulaVieja))
                i.setCedula(cedulaNueva);
    }
    
    /**
     * FUNCIÓN PARA ELIMINAR UN PROFESOR. AL ELIMINARLO TODOS LOS REGISTROS 
     * DONDE EL PROFESOR TIENE CONEXION SE ELIMINAN TAMBIÉN.
     * @param cedula
     * @return 
     */
    public boolean borrarProfesor(String cedula) {
        int option = JOptionPane.showConfirmDialog(null, "Eliminar el profesor " + cedula 
                + " definitivamente? Se borraran todos sus registros como la disponibilidad " 
                +" y los cursos que imparte.");
        if(option == 0) { /* 0: SI, 1: NO, 2: CANCELAR */
            // ELIMINA LOS REGISTROS DE LA RELACIÓN CURSO-PROFESOR
            imparte.removeIf(i -> (i.getCedula().equals(cedula)));
            // ELIMINA LOS REGISTROS DE LOS HORARIOS DE DISPONIBILIDAD
            disponible.removeIf(d -> (d.getCedula().equals(cedula)));
            // ELIMINA EL PROFESOR
            profesores.remove(buscarProfesor(cedula));
            
            /* ELIMINA LAS RELACIONES DEL PROFESOR CON LOS CURSOS */
            sentencia = "DELETE FROM Imparte WHERE cedula = '" + cedula + "'";
            notifyObservers();
            /* ELIMINA LOS HORARIOS DE DISPONIBILIDAD DEL PROFESOR */
            sentencia = "DELETE FROM Disponible WHERE cedula = '" + cedula + "'";
            notifyObservers();
            /* NOTIFICA AL OBSERVADOR PARA QUE ELIMINE EL PROFE DE LA BD */
            sentencia = "DELETE FROM Profesor WHERE cedula = '" + cedula + "'";
            notifyObservers();
            
            JOptionPane.showMessageDialog(null, "Registros eliminados correctamente!");
            return true;
        }
        return false;
    }
    
    /**
     * FUNCIÓN PARA ASIGNARLE UN CURSO A UN PROFESOR. AL RELACIONARLO EL PROFESOR
     * PODRÁ IMPARTIR EL CURSO QUE SE LE HA ASIGNADO. RECIBE LA CEDULA Y EL CÓDIGO
     * DEL CURSO A RELACIONAR.
     * @param cedula
     * @param idCurso
     * @return 
     */
    public boolean relacionarCursoAProfe(String cedula, String idCurso) {
        // VALIDA QUE LA CÉDULA DADA ES VÁLIDA
        if(cedula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor para continuar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // VALIDA QUE EL CÓDIGO DEL CURSO ES VÁLIDO
        if(buscarCurso(idCurso) == null) {
            JOptionPane.showMessageDialog(null, "No existe el curso indicado",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // VALIDA QUE EL PROFESOR NO TENGA YA EL CURSO ASIGNADO
        if(buscarImparte(idCurso, cedula) != null) {
            JOptionPane.showMessageDialog(null, "El profesor ya tiene ese curso asignado",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // AGREGA LA NUEVA RELACIÓN
        imparte.add(new Imparte(idCurso, cedula));
        JOptionPane.showMessageDialog(null, "Curso " + idCurso + " agregado con exito a " 
                + cedula);
        // INSERTA EN LA BASE DE DATOS Y NOTIFICA AL OBSERVADOR
        sentencia = "INSERT INTO Imparte (idCurso, cedula) VALUES ('"+idCurso+"', '"+cedula+"')";
        notifyObservers();
        return true;
    }
    
    /**
     * FUNCIÓN PARA ELIMINARLE UN CURSO A UN PROFESOR. AL QUITARLO EL PROFESOR
     * NO PODRÁ IMPARTIR EL CURSO RECIBIDO POR PARÁMETRO
     * @param cedula
     * @param idCurso
     * @return 
     */
    public boolean quitarCursoAProfe(String cedula, String idCurso) {
        // VALIDA QUE LA CÉDULA DADA ES VÁLIDA
        if(cedula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor para continuar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // VALIDA QUE EL CÓDIGO DEL CURSO ES VÁLIDO
        if(buscarCurso(idCurso) == null) {
            JOptionPane.showMessageDialog(null, "No existe el curso indicado en la base de datos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // VALIDA QUE EL PROFESOR TIENE ESE CURSO ASIGNADO
        if(buscarImparte(idCurso, cedula) == null) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque el "
                    + "profesor no tiene asignado ese curso", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // ELIMINA LA RELACIÓN
        imparte.remove(buscarImparte(idCurso, cedula));
        JOptionPane.showMessageDialog(null, "Curso " + idCurso + " eliminado con exito a " 
                + cedula);
        
        // ELIMINA LA RELACIÓN DE LA BD
        sentencia = "DELETE FROM Imparte WHERE cedula = '" + cedula + "' AND idCurso = '"+ idCurso + "'";
        notifyObservers();
        return true;
    }
    
    /**
     * AGREGA UN HORARIO DE DISPONIBILIDAD EN UNA HORA Y DIA INDICADO AL PROFESOR
     * @param cedula  : CEDULA DEL PROFESOR
     * @param dia     : EN FORMATO -> L, K, M, J, V
     * @param hora    : LA RECIBE EN FORMATO HH:MM - HH-MM
     * @return 
     */
    public boolean agregarDisponibilidadAProfe(String cedula, String dia, String hora) {
        if(cedula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor para continuar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // EXTRAE LAS HORAS SELECCIONADAS
        int horaIni = Integer.parseInt(hora.substring(0, 2));
        int horaFin = Integer.parseInt(hora.substring(8, 10));
        
        // VALIDA QUE EL PROFESOR NO TENGA YA ESE HORARIO DISPONIBLE
        if (buscarDisponible(cedula, dia, horaIni, horaFin) != null) {
            JOptionPane.showMessageDialog(null, "No se puede agregar porque el "
                    + " profesor ya tiene libre ese horario", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // AGREGA LA RELACIÓN
        disponible.add(new Disponible(cedula, dia, horaIni, horaFin));
        
        // ALMACENAMOS EN LA BD EL HORARIO
        sentencia = "INSERT INTO Disponible (cedula, dia, horaIni, horaFin) " + 
               "VALUES ('"+ cedula + "', " + "'" + dia + "', " + horaIni + ", " + horaFin + ')';
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Se ha agregado el horario al profesor " + cedula +
                ", el día " + dia + " de " + hora);
        return true;
    }
    
    /**
     * ELIMINA UN HORARIO DE DISPONIBILIDAD EN UNA HORA Y DIA INDICADO A UN PROFESOR
     * @param cedula  : CEDULA DEL PROFESOR
     * @param dia     : EN FORMATO -> L, K, M, J, V
     * @param hora    : LA RECIBE EN FORMATO HH:MM - HH-MM
     * @return 
     */
    public boolean quitarDisponibilidadAProfe(String cedula, String dia, String hora) {
        if(cedula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor para continuar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // EXTRAE LAS HORAS SELECCIONADAS
        int horaIni = Integer.parseInt(hora.substring(0, 2));
        int horaFin = Integer.parseInt(hora.substring(8, 10));
        
        // VALIDA QUE EL PROFESOR TENGA ESE HORARIO DISPONIBLE
        if (buscarDisponible(cedula, dia, horaIni, horaFin) == null) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque el "
                    + " profesor no tiene ese horario libre.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // REMUEVE LA RELACIÓN
        disponible.remove(buscarDisponible(cedula, dia, horaIni, horaFin));
        
        // ELIMINA DE LA BD LA DISPONIBILIDAD DEL PROFESOR
        sentencia = "DELETE FROM Disponible WHERE cedula = '" + cedula
                + "' AND horaIni = " + horaIni + " AND horaFin = " + horaFin;
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Horario eliminado con exito al profesor " + cedula + "!");
        return true;
    }
    
    /**
     * BUSCA Y GUARDA QUE CURSOS IMPARTE UN PROFESOR PARA DESPREGARLOS EN PANTALLA
     * @param cedula
     * @return 
     */
    public DefaultListModel cursosQueImparte(String cedula) {
        DefaultListModel nuevo = new DefaultListModel();
        for (Imparte i: imparte) {
            if(i.getCedula().equals(cedula)) {
                nuevo.addElement(i.getIdCurso() + ", " 
                        + buscarCurso(i.getIdCurso()).getNombre());
            }
        }
        return nuevo;
    }
    
    
    //*************************************************************************
    //********************* METODOS PARA LOS CURSOS****************************
    //*************************************************************************
    
    // FUNCIÓN PRINCIPAL PARA REGISTRAR CURSOS
    public boolean insertarCurso(String nombre, int cr, int sem, String tipo, int clasesXSem) {
        if(nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo nombre vacio",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String idCurso = "IC";
        int codigo;
        Random r = new Random();
        // GENERA UN CÓDIGO VÁLIDO DE 4 DÍGITOS PARA PARA EL CURSO
        do {
            codigo = 1000 + r.nextInt((10000) - 1000);
        } while(buscarCurso(idCurso + codigo) != null);
        // CONCATENA EL "IC" CON EL CÓDIGO
        idCurso += codigo;
        // INSERTA
        this.cursos.add(new Curso(idCurso, nombre, cr, sem, tipo, clasesXSem));
        
        // SENTENCIA PARA ALMACENAR EL CURSO EN LA BASE DE DATOS
        sentencia = "INSERT INTO Curso (idCurso, nombre, creditos, semestre, tipo, clasesXSemana) " + 
               "VALUES ('"+ idCurso+"', '" + nombre + "', " + cr + ", " + sem + ", '" +  tipo + "', " + clasesXSem + ')';
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Curso " + nombre + " agregado correctamente!");
        return true;
    }
    
    // FUNCIÓN PARA MODIFICAR UN CURSO. EL CÓDIGO DE LOS CURSOS NUNCA SE EDITAN
    // POR LO QUE NO ES NECESARIO MODIFICAR OTROS REGISTROS.
    public boolean editarCurso(String id, String nombre, int cr, int sem, String tipo, int clasesXSem) {
        if(nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo nombre vacio",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // BUSCAR LA DIRECCIÓN DE MEMORIA DEL CURSO
        Curso c = buscarCurso(id);
        // PARA LUEGO CAMBIAR SUS DATOS
        c.setNombre(nombre);
        c.setCreditos(cr);
        c.setSemestre(sem);
        c.setTipo(tipo);
        c.setClasesXSem(clasesXSem);
        
        // NOTIFICA AL OBSERVADOR PARA QUE ACTUALICE LOS DATOS
        sentencia = "UPDATE Curso SET nombre = '" + nombre + "', creditos = " + cr 
                + ", semestre = " + sem + ", tipo = '" + tipo + "', clasesXSemana = " + clasesXSem 
                + " WHERE idCurso = '" + id + "'";
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Curso " + id + " Modificado correctamente!");
        return true;
    }
    
    /**
     * FUNCIÓN PARA ELIMINAR UN CURSO. AL ELIMINARLO TODOS LOS REGISTROS
     * DONDE EL CURSO TIENE RELACIÓN SE ELIMINAN.
     * @param idCurso
     * @return
     */
    public boolean borrarCurso(String idCurso) {
        // VALIDA LA EXISTENCIA DEL CURSO
        if(buscarCurso(idCurso) == null) {
            JOptionPane.showMessageDialog(null, "El curso no existe indicado!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Curso c = buscarCurso(idCurso);
        String nombre = c.getNombre();
        int option = JOptionPane.showConfirmDialog(null, "Eliminar el curso " + nombre 
                + " definitivamente? Se perderán todos los registros asociados.");
        if(option == 0) { /* 0: SI, 1: NO, 2: CANCELAR */
            
            // ELIMINA LAS RELACIONES DE CURSO CON PROFESOR
            imparte.removeIf(i -> (i.getIdCurso().equals(idCurso)));
            // ELIMINA EL CURSO DE LA LISTA
            cursos.remove(c);

            // ELIMINA DE LA BD LAS RELACIONES ENTRE CURSO Y PROFESOR
            sentencia = "DELETE FROM Imparte WHERE idCurso = '" + idCurso + "'";
            notifyObservers();
            
            // SENTENCIA PARA ELIMINAR EL CURSO DE LA BD
            sentencia = "DELETE FROM Curso WHERE idCurso = '" + idCurso + "'";
            notifyObservers();
        
            JOptionPane.showMessageDialog(null, "El curso " + nombre + " fue eliminado correctamente!");
            return true;
        }
        return false;
    }
    
    
    
    //*************************************************************************
    //********************* METODOS PARA LAS AULAS*****************************
    //*************************************************************************
    
    /**
     * FUNCIÓN PARA REGISTRAR AULAS. REALIZA VALIDACIONES PARA EVITAR CONFLICTOS 
     * CON LOS DATOS. RETORNA TRUE SI EL REGISTRO SE INSERTA Y FALSE SI NO.
     * @param idAula
     * @param tipo
     * @param capacidad
     * @return 
     */
    public boolean insertarAula(String idAula, String tipo, int capacidad) {
        // VALIDA QUE EL ID NO SE REPITA
        if (buscarAula(idAula) != null) {
            JOptionPane.showMessageDialog(null, "El nombre de la aula indicada ya existe!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // REGISTRA EL AULA
        aulas.add(new Aula(idAula, tipo, capacidad));
        
        // NOTIFICA AL OBSERVADOR QUE SE HA CREADO UN NUEVO DATO
        sentencia = "INSERT INTO Aula (idAula, tipo, capacidad) " + 
               "VALUES ('"+idAula+"', " + "'"+ tipo +"', " + capacidad + ')';
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "La aula " + idAula + " se insertó correctamente!");
        return true;
    }
    
    /**
     * FUNCIÓN PARA MODIFICAR UN AULA. SE MODIFICA, EN CASO DE QUE SE EDITE EL
     * ID, LA LISTA CON LA DISPONIBILIDAD DE LAS AULAS
     * @param viejoId
     * @param nuevoId
     * @param tipo
     * @param capacidad
     * @return 
     */
    public boolean editarAula(String viejoId, String nuevoId, String tipo, int capacidad) {
        // VALIDA QUE NO HAYA CAMPOS NULOS
        if(nuevoId.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo vacio, inserte la información necesaria",
                    "Vacio", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // VALIDA QUE NO EXISTA YA UN AULA CON ESE ID
        if(buscarAula(nuevoId) != null && ! viejoId.equals(nuevoId)) {
            JOptionPane.showMessageDialog(null, "Ya existe una aula con ese nombre",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Aula aula = buscarAula(viejoId);
        // MODIFICA LOS DATOS EN LA APLICACIÓN
        aula.setCapacidad(capacidad);
        aula.setTipo(tipo);
        // EN CASO DE MODIFICAR EL ID, SE MODIFICA TAMBIÉN EN OTROS REGISTROS
        if( ! viejoId.equals(nuevoId) ) {
            // MODIFICA EL ID
            aula.setIdAula(nuevoId);
            // ACTUALIZA EL ID EN LOS HORARIOS
            actualizarHorariosAula(viejoId, nuevoId);
            // ACTUALIZA EN LA BD EL AULA
            sentencia = "UPDATE Aula SET idAula = '" + nuevoId + "', tipo = '" + tipo + "', capacidad = " + capacidad
                    + " WHERE idAula = '" + viejoId + "'";
            notifyObservers();
            /* MODIFICA LA TABLA DE DISPONIBILIDAD DEL AULA EN LA BD */
            sentencia = "UPDATE DisponibleA SET idAula = '" + nuevoId + "' WHERE idAula = '" + viejoId + "'";
            notifyObservers();
        } else {
            // EN CASO DE QUE NO SE MODIFIQUE EL ID DEL AULA
            sentencia = "UPDATE Aula SET tipo = '" + tipo + "', capacidad = " + capacidad
                    + " WHERE idAula = '" + viejoId + "'";
            notifyObservers();
        }
        JOptionPane.showMessageDialog(null, "Aula modificada correctamente!");
        return true;    
    }
    
    /**
     * SE EJECTUTA AL MODIFICAR EL ID DE UN AULA.
     * CAMBIAR EL ID EN LA RELACIÓN DEL AULA CON EL HORARIO DE DISPONIBILIDAD
     * @param viejoId
     * @param nuevoId 
     */
    private void actualizarHorariosAula(String viejoId, String nuevoId) {
        for (DisponibleA da : disponibleA)
            if(da.getIdAula().equals(viejoId))
                da.setIdAula(nuevoId);
    }
    
    /**
     * ELIMINA AULAS Y ELIMINA SUS HORARIOS DE DISPONIBILIDAD.
     * @param idAula
     * @return 
     */
    public boolean borrarAula(String idAula) {
        Aula aula = buscarAula(idAula);
        if(aula == null) {
            JOptionPane.showMessageDialog(null, "El aula indicada no existe!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // ELIMINA LOS HORARIOS DE DISPONIBILIDAD DEL AULA
        disponibleA.removeIf(a -> (a.getIdAula().equals(idAula)));
        // BORRA EL AULA
        aulas.remove(buscarAula(idAula));
        
        // SENTENCIA PARA ELIMINAR EL AULA EN LA BASE DE DATOS
        sentencia = "DELETE FROM Aula WHERE idAula = '" + idAula + "'";
        notifyObservers();
        
        // ELIMINA DE LA BD LA DISPONIBILIDAD DEL AULA
        sentencia = "DELETE FROM DisponibleA WHERE idAula = '" + idAula + "'";
        notifyObservers();
        JOptionPane.showMessageDialog(null, "El aula " + idAula + " se eliminó correctamente!");
        return true;
    }
    
    /**
     * AGREGA HORARIOS DE DISPONIBILIDAD AL AULA INDICADA. RECIBE EL DÍA
     * Y LA HORA A INSERTAR
     * @param idAula : IDENTIFICADOR DEL AULA
     * @param dia    : DIA EN FORMATO -> L, K, M, J, V
     * @param hora   : FORMATO HH:MM - HH:MM
     * @return 
     */
    public boolean agregarDisponibilidadAula(String idAula, String dia, String hora) {
        if(idAula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un aula para continuar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // EXTRAE LAS HORAS SELECCIONADAS
        int horaIni = Integer.parseInt(hora.substring(0, 2));
        int horaFin = Integer.parseInt(hora.substring(8, 10));
        
        // VALIDA QUE EL AULA NO TENGA YA ESE HORARIO DISPONIBLE
        if (buscarDisponibleA(idAula, dia, horaIni, horaFin) != null) {
            JOptionPane.showMessageDialog(null, "No se puede agregar porque el "
                    + " aula ya tiene libre ese horario", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // AGREGA EL NUEVO HORARIO AL AULA
        disponibleA.add(new DisponibleA(idAula, dia, horaIni, horaFin));
        
        // ALMACENAMOS EN LA BD EL HORARIO
        sentencia = "INSERT INTO DisponibleA (idAula, dia, horaIni, horaFin) " + 
               "VALUES ('"+ idAula + "', " + "'" + dia + "', " + horaIni + ", " + horaFin + ')';
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Se ha agregado el horario al aula " + idAula +
                ", el día " + dia + " de " + hora);
        return true;
    }
    
    /**
     * ELIMINAR LA DISPONIBILIDAD A UN AULA INDICADA
     * @param idAula : IDENTIFICADOR DEL AULA
     * @param dia    : DIA EN FORMATO -> L, K, M, J, V
     * @param hora   : FORMATO HH:MM - HH:MM
     * @return 
     */
    public boolean quitarDisponibilidadAula(String idAula, String dia, String hora) {
        if(idAula == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un aula para continuar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // EXTRAE LAS HORAS SELECCIONADAS
        int horaIni = Integer.parseInt(hora.substring(0, 2));
        int horaFin = Integer.parseInt(hora.substring(8, 10));
        
        // VALIDA QUE EL AULA TENGA SI TENGA ESE HORARIO
        if (buscarDisponibleA(idAula, dia, horaIni, horaFin) == null) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque el "
                    + " aula no tiene ese horario libre.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // ELIMINA LA DISPONIBILIDAD DEL AULA
        disponibleA.remove(buscarDisponibleA(idAula, dia, horaIni, horaFin));
        
        // ELIMINA DE LA BD LA DISPONIBILIDAD DEL AULA
        sentencia = "DELETE FROM DisponibleA WHERE idAula = '" + idAula 
                + "' AND horaIni = " + horaIni + " AND horaFin = " + horaFin;
        notifyObservers();
        
        JOptionPane.showMessageDialog(null, "Horario eliminado con exito al aula " + idAula + "!");
        return true;
    }
    
    
    //*************************************************************************
    //********************* METODOS DE BUSQUEDA *******************************
    //*************************************************************************
    public Profesor buscarProfesor(String cedula) {
        for (Profesor p : profesores)
            if(p.getCedula().equals(cedula))
                return p;
        return null;
    }
    
    public Aula buscarAula (String idAula) {
        for (Aula a : aulas)
            if(a.getIdAula().equals(idAula))
                return a;
        return null;
    }
    
    public Curso buscarCurso (String idCurso) {
        for (Curso c : cursos)
            if(c.getIdCurso().equals(idCurso))
                return c;
        return null;
    }
    
    private Imparte buscarImparte (String idCurso, String cedula) {
        for (Imparte i : imparte)
            if(i.getIdCurso().equals(idCurso) && i.getCedula().equals(cedula))
                return i;
        return null;
    }
    
    private Disponible buscarDisponible(String cedula, String dia, int horaIni, int horaFin) {
        for (Disponible d : disponible)
            if(d.getCedula().equals(cedula) && d.getDia().equals(dia) 
                    && d.getHoraIni() == horaIni && d.getHoraFin() == horaFin)
                return d;
        return null;
    }
    
    private DisponibleA buscarDisponibleA(String idAula, String dia, int horaIni, int horaFin) {
        for (DisponibleA d : disponibleA)
            if(d.getIdAula().equals(idAula) && d.getDia().equals(dia) 
                    && d.getHoraIni() == horaIni && d.getHoraFin() == horaFin)
                return d;
        return null;
    }
    
    
    
    
    
    
    //*************************************************************************
    //************************ METODOS PARA IMPRIMIR ***************************
    //*************************************************************************
    
    public void imprimirP(ArrayList<Profesor> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
    public void imprimirA(ArrayList<Aula> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
    public void imprimirC(ArrayList<Curso> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
    public void imprimirI(ArrayList<Imparte> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
    public void imprimirD(ArrayList<Disponible> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
    public void imprimirDA(ArrayList<DisponibleA> lista) {
        for (Object objeto : lista) {
            System.out.println(objeto.toString());
            System.out.println("__________________________________");
        }
    }
    
}

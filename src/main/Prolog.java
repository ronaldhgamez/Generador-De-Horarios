package main;

import java.util.*;
import javax.swing.JOptionPane;
import models.*;
import org.jpl7.PrologException;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 * CLASE QUE CONTIENE LOS METODOS PARA HACER LAS CONSULTAS A PROLOG, SUBIR LOS
 * HECHOS ENTRE OTRAS FUNCIONES
 * @author Ronaldhg
 */
public class Prolog {
    
    private Query query;
    public Listas lista;

    public Prolog() {
        this.query = null;
        this.lista = Listas.getInstance();
    }
    
    /**
     * FUNCIÓN PARA CONSULTAR LAS REGLAS DE PROLOG Y SUBIR LOS HECHOS
     * VALIDA SI HUBIERON CAMBIOS EN LAS LISTAS DEL PROGRAMA.
     * DE SER ASÍ, SE BORRAN LOS HECHOS Y VUELVE A INSERTARLOS ACTUALIZADOS
     */
    public void validar() {
        // REALIZA EL CONSULT A LOS DATOS POR PRIMERA VEZ
        if(query == null) {
            System.out.println("Cargando los datos prolog primera vez");
            cargarProlog();
        } else { // YA ESTÁ INICIADO
            // VALIDA SI HUBO CAMBIOS EN LAS LISTAS
            if(lista.fueModificado) {
                // REMUEVE LOS HECHOS Y VUELVE A SUBIR LOS DATOS A PROLOG
                System.out.println("----------------------------------------------");
                System.out.println("LOS DATOS FUERON MODIFICADOS!");
                removerHechos();
                /* VUELVE A SUBIR LOS DATOS A PROLOG */
                cargarProlog();
                System.out.println("CARGANDO HECHOS NUEVAMENTE!");
                lista.fueModificado = false;
            }
        }
    }
    
    private void cargarProlog() {
        /* CONSULTA LAS REGLAS DEL ARCHIVO PL */
        if( ! consultarQuery() ) {
            return;
        }
        /* INSERTA AL INTERPRETE DE PROLOG LOS HECHOS */
        cargarDatosAProlog();
    }
    
    public boolean consultarQuery() {
        try {
            String c = "consult('backend.pl')";
            query = new Query(c);
            query.hasSolution();
            return true;
        } catch (PrologException pe) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo " +
                    ".pl\n" + pe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * RECORRE LAS LISTAS DEL PROGRAMA Y CARGA LOS DATOS AL INTERPRETE
     * DE PROLOG.
     */
    private void cargarDatosAProlog() {
        
        /* CARGA LOS HECHOS PARA LAS AULAS */
        ArrayList<Aula> aulas = lista.aulas;
        String hecho = "aula(";
        for (Aula a : aulas) {
            hecho += "'" + a.getIdAula() + "', " + a.getTipo() +
                    ", "+ a.getCapacidad() + ')';
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "aula(";
        }
        
        /* CARGA LOS HECHOS PARA LOS CURSOS */
        ArrayList<Curso> cursos = lista.cursos;
        hecho = "curso(";
        for (Curso c : cursos) {
            hecho += "'" + c.getIdCurso()+ "', '" + c.getNombre() +
                    "', "+ c.getCreditos()+ ", " + c.getSemestre()
                    + ", " + c.getTipo() + ", " + c.getClasesXSem() + ')';
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "curso(";
        }
        
        /* CARGA LOS HECHOS PARA LOS PROFESORES */
        ArrayList<Profesor> profesores = lista.profesores;
        hecho = "profesor(";
        for (Profesor p : profesores) {
            hecho += "'" + p.getCedula()+ "', '" + p.getNombre() + "')";
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "profesor(";
        }
        
        /* CARGA LOS HECHOS PARA LA RELACION IMPARTE */
        ArrayList<Imparte> imparte = lista.imparte;
        hecho = "imparte(";
        for (Imparte i : imparte) {
            hecho += "'" + i.getIdCurso()+ "', '" + i.getCedula() + "')";
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "imparte(";
        }
        
        /* CARGA LOS HECHOS LA DISPONIBILIDAD DE LOS PROFES */
        ArrayList<Disponible> disponible = lista.disponible;
        hecho = "disponible(";
        for (Disponible d : disponible) {
            hecho += "'" + d.getCedula()+ "', '" + d.getDia() + "', "
                    + d.getHoraIni() + ", " + d.getHoraFin() + ')';
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "disponible(";
        }
        
        /* CARGA LOS HECHOS LA DISPONIBILIDAD DE LAS AULAS */
        ArrayList<DisponibleA> disponibleA = lista.disponibleA;
        hecho = "disponibleA(";
        for (DisponibleA d : disponibleA) {
            hecho += "'" + d.getIdAula()+ "', '" + d.getDia() + "', "
                    + d.getHoraIni() + ", " + d.getHoraFin() + ')';
            // INSERTA EL HECHO
            insertarHecho(hecho);
            hecho = "disponibleA(";
        }
    }
    
    /**
     * INSERTA EN EL INTERPRETE DE PROLOG EL HECHO RECIBIDO POR PARÁMETRO
     * @param hecho 
     */
    private void insertarHecho(String hecho) {
        try {
            String c = "assert(" + hecho + ").";
            query = new Query(c);
            // EJECUTA LA INSTRUCCION
            query.hasSolution();
        } catch (PrologException pe) {
            System.out.println("Error al insertar: " + pe.getMessage());
        }
    }
    
    public ArrayList<ArrayList<String[]>> consulta4(String cursos, int totComb) {
        
        // REALIZA LA CONSULTA CON LA LISTA CREADA ANTERIORMENTE
        String consulta = "gen([" + cursos + "], X).";
        query = new Query(consulta);
        
        if (query.hasSolution()) {
            Map<String, Term> x; // --> LO QUE RECIBE DE PROLOG
            Map <String, String> colorCurso = new HashMap<>();
            
            ArrayList<ArrayList<String[]>> soluciones = new ArrayList<>();
            ArrayList<String[]> solucion;
            
            // EL WHILE SE UTILIZA PARA IR OBTENIENDO LAS SOLUCIONES DESDE PROLOG
            // UTILIZANDO BACKTRAKING. PARECIDO A PONER ; EN PROLOG PARA SEGUIR
            // OBTENIENDO RESULTADOS.
            while (query.hasMoreSolutions() && totComb > 0) {
                totComb--;
                
                // SE OBTIENE UNA SOLUCIÓN A UN POSIBLE HORARIO.
                // EL FORMATO DE LA LISTA RECIBIDA ES UNA LISTA DE LISTAS.
                x = query.nextSolution();
                
                // SEPARA LAS LISTAS Y LAS INSERTA EN UN ARREGLO.
                Term[] termArray = x.get("X").toTermArray();
                
                // Almacenar una combinacion de los horarios generados.
                solucion = new ArrayList<>(); // SOLUCION A UNA COMBINACION
                String[] horario; // DIA QUE SE DA UN CURSO Y LA INFORMACION
                
                int color = 0;
                for (Term term : termArray) {
                    // PARA ACCEDER A LOS TERMINOS DE LAS LISTAS.
                    Term[] termArray2 = term.toTermArray();
                    
                    // ALMACENA TEMPORALMENTE LA INFO DE LAS LISTAS.
                    horario = new String[5];
                    horario[0] = termArray2[0].toString(); //DIA
                    horario[1] = termArray2[1].toString(); // IDCURSO
                    horario[2] = termArray2[2].toString(); // HORAINICIAL
                    horario[3] = termArray2[3].toString(); // HORAFINAL
                    
                    if( ! colorCurso.containsKey(horario[1]) ) {
                        colorCurso.put(horario[1], color + "");
                        color++;
                    }
                    horario[4] = colorCurso.get(horario[1]);
                    solucion.add(horario);
                }
                // AÑADIMOS EL HORARIO A LA LISTA GENERAR DE SOLUCIONES
                soluciones.add(solucion);
            }
            return soluciones;
        } else {
            JOptionPane.showMessageDialog(null, "No es posible generar combinaciones " +
                    "para los cursos seleccionados.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
    public ArrayList<ArrayList<String[]>> consulta2Y5(int numSem, int totComb, int consult) {
        
        String consulta;
        int tam;
        
        // PREPARA LA CONSULTA A EJECUTAR
        if(consult == 2) {
            consulta = "consulta2(" + numSem + ", X).";
            tam = 6;
        } else {
            consulta = "gen2(" + numSem + ", X).";
            tam = 7;
        }
        // EJECUTA LA CONSULTA
        query = new Query(consulta);
        
        if (query.hasSolution()) {
            
            Map<String, Term> x; // --> LO QUE RECIBE DE PROLOG
            Map <String, String> colorCurso = new HashMap<>();
            ArrayList<ArrayList<String[]>> soluciones = new ArrayList<>();
            ArrayList<String[]> solucion;
            
            // EL WHILE SE UTILIZA PARA IR OBTENIENDO LAS SOLUCIONES DESDE PROLOG
            // UTILIZANDO BACKTRAKING. PARECIDO A PONER ; EN PROLOG PARA SEGUIR
            // OBTENIENDO RESULTADOS.
            while (query.hasMoreSolutions() && totComb > 0) {
                totComb--;
                // SE OBTIENE UNA SOLUCIÓN A UN POSIBLE HORARIO.
                // LO QUE SE RECIBE DESDE PROLOG ES UNA LISTA DE LISTAS.
                // System.out.println("-----------------------------------------");
                x = query.nextSolution();
                
                // SEPARA LAS LISTAS Y LAS INSERTA EN UN ARREGLO.
                Term[] termArray = x.get("X").toTermArray();
                
                // Almacenar una combinacion de los horarios generados.
                solucion = new ArrayList<>(); // SOLUCION A UNA COMBINACION
                String[] horario; // DIA QUE SE DA UN CURSO Y LA INFORMACION
                
                int color = 0;
                for (Term term : termArray) {
                    // PARA ACCEDER A LOS TERMINOS DE LAS LISTAS.
                    Term[] termArray2 = term.toTermArray();
                    
                    if(consult == 2) {
                        horario = new String[tam];
                    } else {
                        horario = new String[tam];
                    }
                    // ALMACENA TEMPORALMENTE LA INFO DE LAS LISTAS.
                    horario[0] = termArray2[0].toString(); // DIA
                    horario[1] = termArray2[1].toString(); // HORA INICIO
                    horario[2] = termArray2[2].toString(); // HORA FINAL
                    horario[3] = termArray2[3].toString(); // ID CURSO
                    horario[4] = termArray2[4].toString(); // CEDULA PROFESOR
                    // EN CASO DE QUE SEA LA CONSULTA 5
                    if(consult == 5) {
                        horario[5] = termArray2[5].toString(); // ID AULA
                    }
                    
                    if( ! colorCurso.containsKey(horario[3]) ) {
                        colorCurso.put(horario[3], color + "");
                        color++;
                    }
                    horario[tam - 1] = colorCurso.get(horario[3]);
                    solucion.add(horario);
                }
                // AÑADIMOS EL HORARIO A LA LISTA GENERAR DE SOLUCIONES
                soluciones.add(solucion);
            }
            return soluciones;
        } else {
            JOptionPane.showMessageDialog(null, "No es posible generar combinaciones " +
                    "para el semestre seleccionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
    /**
     * AL HACER ALGUNA MODIFICACIÓN SE ELIMINAN LOS HECHOS DE PROLOG Y SE VUELVEN
     * A INSERTAR.
     */
    public void removerHechos() {
        String c = "retractall(?).";
        String s;
        try{
            s = c.replace("?", "aula(_,_,_)");
            query = new Query(s);
            query.hasSolution();
            
            s = c.replace("?", "disponibleA(_,_,_,_)");
            query = new Query(s);
            query.hasSolution();
            
            s = c.replace("?", "profesor(_,_)");
            query = new Query(s);
            query.hasSolution();
            
            s = c.replace("?", "imparte(_,_)");
            query = new Query(s);
            query.hasSolution();
            
            s = c.replace("?", "disponible(_,_,_,_)");
            query = new Query(s);
            query.hasSolution();
            
            s = c.replace("?", "curso(_,_,_,_,_,_)");
            query = new Query(s);
            query.hasSolution();
            System.out.println("Hechos borrados de prolog!\n");
            
        } catch (PrologException pe) {
            System.out.println("Error al borrar: " + pe.getMessage());
        }
    }
    
    public void cerrarQuery() {
        this.query.close();
        this.query = null;
    }
    
    public void listarHecho(String hecho) {
        try{
            String c = "listing(" + hecho + ").";
            query = new Query(c);
            query.hasSolution();
        } catch (PrologException pe) {
            System.out.println("Error al listar: \n" + pe.getMessage());
        }
    }
    
    public void exportarHechosA_SQLite() {
        
        // CONEXION A LA BASE DE DATOS
        SQLite bd = new SQLite();
        if(! bd.conectado()) {
            return;
        }
        // PRIMERO BORRAR TODOS LOS REGISTROS ANTERIORES
        bd.deleteAll();
        
        query = new Query("aula(X, Y, Z)");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String idAula, tipo;
            int capacidad;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                idAula = m.get("X").toString();
                tipo = m.get("Y").toString();
                capacidad = Integer.parseInt(m.get("Z").toString());
                
                System.out.println(idAula + " " + tipo + " " + capacidad);
                
                // INSERTA EL HECHO EN LA BASE DE DATOS
                String c = "INSERT INTO Aula (idAula, tipo, capacidad) "
                        + "VALUES (" + idAula + ", '" + tipo + "', " + 
                        capacidad + ')';
                bd.ejecutarQuery(c);
            }
        }
        
        System.out.println("");
        query = new Query("profesor(Ced, Nom)");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String cedula, nombre;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                cedula = m.get("Ced").toString();
                nombre = m.get("Nom").toString();
                System.out.println(cedula + " " + nombre);
                
                // INSERTA EL HECHO EN LA BASE DE DATOS
                String c = "INSERT INTO Profesor (cedula, nombre) "
                        + "VALUES (" + cedula + ", " + nombre + ')';
                bd.ejecutarQuery(c);
            }
        }
        System.out.println("");
        query = new Query("curso(IdCur, Nom, Cre, Sem, Tipo, CXS).");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String idCurso, nombre, tipo;
            int creditos, semestre, ClasesXSem;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                idCurso = m.get("IdCur").toString();
                nombre = m.get("Nom").toString();
                creditos = Integer.parseInt(m.get("Cre").toString());
                semestre = Integer.parseInt(m.get("Sem").toString());
                tipo = m.get("Tipo").toString();
                ClasesXSem = Integer.parseInt(m.get("CXS").toString());
                System.out.println(idCurso + " " + nombre + " " + creditos +
                        " " + semestre + " " + tipo + " " + ClasesXSem);
                
                // INSERTA EL HECHO EN LA BASE DE DATOS SQLite
                String c = "INSERT INTO Curso (idCurso, nombre, creditos, semestre, "
                        + "tipo, clasesXSemana)" + " VALUES (" + idCurso + ", " 
                        + nombre + ", " + creditos + ", " + semestre + ", '" 
                        + tipo + "', " + ClasesXSem + ')';
                bd.ejecutarQuery(c);
            }
        }
        System.out.println("");
        query = new Query("imparte(IdCur, Ced)");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String idCurso, cedula;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                idCurso = m.get("IdCur").toString();
                cedula = m.get("Ced").toString();
                System.out.println(idCurso + " " + cedula);
                
                // INSERTA EL HECHO EN LA BASE DE DATOS SQLite
                String c = "INSERT INTO Imparte (idCurso, cedula) "
                        + "VALUES (" + idCurso + ", " + cedula + ')';
                bd.ejecutarQuery(c);
            }
        }
        System.out.println("");
        query = new Query("disponible(Ced, Dia, Ini, Fin)");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String cedula, dia;
            int ini, fin;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                cedula = m.get("Ced").toString();
                dia = m.get("Dia").toString();
                ini = Integer.parseInt(m.get("Ini").toString());
                fin = Integer.parseInt(m.get("Fin").toString());
                
                System.out.println(cedula + " " + dia + " " + ini +
                        " " + fin);
                
                // INSERTA EL HECHO EN LA BASE DE DATOS SQLite
                String c = "INSERT INTO Disponible (cedula, dia, horaIni, horaFin)"
                        + " VALUES (" + cedula + ", " + dia + ", " + ini + ", " + fin + ')';
                bd.ejecutarQuery(c);
            }
        }
        System.out.println("");
        query = new Query("disponibleA(IdAul, Dia, Ini, Fin)");
        if(query.hasSolution()) {
            Map<String, Term> m;
            String idAula, dia;
            int ini, fin;
            while(query.hasMoreSolutions()){
                m = query.nextSolution();
                idAula = m.get("IdAul").toString();
                dia = m.get("Dia").toString();
                ini = Integer.parseInt(m.get("Ini").toString());
                fin = Integer.parseInt(m.get("Fin").toString());
                
                System.out.println(idAula + " " + dia + " " + ini +
                        " " + fin);
                // INSERTA EL HECHO EN LA BASE DE DATOS SQLite
                String c = "INSERT INTO DisponibleA (idAula, dia, horaIni, horaFin)"
                        + " VALUES (" + idAula + ", " + dia + ", " + ini + ", " + fin + ')';
                bd.ejecutarQuery(c);
            }
        }
        bd.cerrarConexion();
    }

}

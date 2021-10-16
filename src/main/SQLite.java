package main;

import java.sql.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import models.*;

/**
 * CONTIENE LAS INSTRUCCIONES PARA REALIZAR LA CONEXION A LA BASE DE DATOS
 * Y LAS INSTRUCCIONES COMO EJECUTAR SENTENCIAS SQL, INSERTAR, ELIMINAR
 * SELECCIONAR TABLAS Y CERRAR CONEXION.
 * IMPLEMENTA LA INTERFAZ OBSERVER PARA QUE AL MODIFICAR ALGUN DATO EN LAS LISTAS
 * DEL PROGRAMA SE LE NOTIFIQUE Y HAGA LOS CAMBIOS
 * @author Ronaldhg
 */
public class SQLite implements Observer {
    
    private Connection conexion;
    private Statement statement;
    private Listas lista;

    public SQLite() {
        this.conexion = null;
        this.statement = null;
        this.lista = null;
    }
    
    /**
     * INTENTA ESTABLECER UNA CONEXION CON LA ULR DE LA BD DADA
     * RETORNA TRUE SI LA CONEXION TUVO EXITO, FALSE EN CASO CONTRARIO
     * @return 
     */
    public boolean conectado() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:horarios.db");
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido obtener una conexion con la base."
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * SELECCIONA TODOS LOS REGISTROS DE UNA TABLA RECIBIDA POR PARAMETRO
     * RETORNA UN SET CON LOS REGISTROS DE LA TABLA O NULL EN CASO DE ERROR
     * @param tabla
     * @return 
     */
    public ResultSet select(String tabla) {
        try{
            String query = "SELECT * FROM " + tabla;
            statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(query);
            return resultado;
        }catch (SQLException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al seleccionar tabla: \n" 
                      + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /***
     * EJECUTA SENTENCIAS SQL COMO INSERT, DELETE O UPDATE
     * RECIBE LA INSTRUCCION POR PARAMETRO PARA SU EJECUCION
     * RETORNA TRUE SI LA EJECUCION TUVO EXITO
     * @param query 
     * @return
     */
    public boolean ejecutarQuery(String query){
        try{
            statement = conexion.createStatement();
            statement.execute(query);
            // Query ejecutado correctamente
            return true;
        }catch (SQLException e){
              JOptionPane.showMessageDialog(null, "Error al ejecutar el query: \n" 
                      + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              return false;
        }
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
            conexion = null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar \n" 
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * ENCARGADA DE IMPORTAR LOS REGISTROS DE LAS TABLAS DE LA BD A LAS LISTAS
     * DEL PROGRAMA. POR CADA REGISTRO CREA UN OBJETO Y LO INSERTA EN SU LISTA
     * RESPECTIVA
     */
    public void descargarDatos() {
        lista = Listas.getInstance();
        cargarProfesores();
        cargarCursos();
        cargarAulas();
        cargarRelacionProfeCurso();
        cargarDisponibilidad("Disponible", "cedula");
        cargarDisponibilidad("DisponibleA", "idAula");
    }
    
    private void cargarProfesores() {
        ResultSet result = select("Profesor");
        try{
            String cedula, nombre;
            while(result.next()){
                /* EXTRAE LOS REGISTROS DESDE LA BASE DE DATOS */
                cedula = result.getString("cedula");
                nombre = result.getString("nombre");
                
                /* INSERTA EL REGISTRO EN LA LISTA DEL PROGRAMA */
                lista.profesores.add(new Profesor(cedula, nombre));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los registros de la BD"
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarCursos() {
        ResultSet result = select("Curso");
        try{
            String idCurso, nombre, tipo;
            int creditos, semestre, clasesXSem; 
            while(result.next()){
                /* EXTRAE LOS REGISTROS DESDE LA BASE DE DATOS */
                idCurso = result.getString("idCurso");
                nombre = result.getString("nombre");
                creditos = result.getInt("creditos");
                semestre = result.getInt("semestre");
                tipo = result.getString("tipo");
                clasesXSem = result.getInt("clasesXSemana");
                
                /* INSERTA EL REGISTRO EN LA LISTA DEL PROGRAMA */
                lista.cursos.add(new Curso(idCurso, nombre, creditos, 
                        semestre, tipo, clasesXSem));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los registros de la BD"
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarAulas() {  
        ResultSet result = select("Aula");
        try{
            String idAula, tipo;
            int capacidad;
            while(result.next()){
                /* EXTRAE LOS REGISTROS DESDE LA BASE DE DATOS */
                idAula = result.getString("idAula");
                tipo = result.getString("tipo");
                capacidad = result.getInt("capacidad");
                
                /* INSERTA EL REGISTRO EN LA LISTA DEL PROGRAMA */
                lista.aulas.add(new Aula(idAula, tipo, capacidad));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los registros de la BD"
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarRelacionProfeCurso() {
        ResultSet result = select("Imparte");
        try{
            String idCurso, cedula;
            while(result.next()){
                /* EXTRAE LOS REGISTROS DESDE LA BASE DE DATOS */
                idCurso = result.getString("idCurso");
                cedula = result.getString("cedula");
                
                /* INSERTA EL REGISTRO EN LA LISTA DEL PROGRAMA */
                lista.imparte.add(new Imparte(idCurso, cedula));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los registros de la BD"
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDisponibilidad(String tabla, String columna) {
        ResultSet result = select(tabla);
        try{
            String id, dia;
            int horaIni, horaFin;
            while(result.next()){
                /* EXTRAE LOS REGISTROS DESDE LA BASE DE DATOS */
                id = result.getString(columna);
                dia = result.getString("dia");
                horaIni = result.getInt("horaIni");
                horaFin = result.getInt("horaFin");
                
                /* INSERTA EL REGISTRO EN LA LISTA DEL PROGRAMA */
                if(tabla.equals("Disponible")) {
                    lista.disponible.add(new Disponible(id, dia, horaIni, horaFin));
                } else {
                    lista.disponibleA.add(new DisponibleA(id, dia, horaIni, horaFin));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los registros de la BD"
                    + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * ELIMINA TODOS LOS REGISTROS DE LA BASE DE DATOS EN CASO DE HABER HECHO
     * MODIFICACIONES. NO SE UTILIZA UNA VEZ TERMINADO EL PROGRAMA.
     */
    public void deleteAll() {
        String c = "DELETE FROM Profesor";
        ejecutarQuery(c);
        c = "DELETE FROM Aula";
        ejecutarQuery(c);
        c = "DELETE FROM Curso";
        ejecutarQuery(c);
        c = "DELETE FROM Imparte";
        ejecutarQuery(c);
        c = "DELETE FROM Disponible";
        ejecutarQuery(c);
        c = "DELETE FROM DisponibleA";
        ejecutarQuery(c);
    }

    @Override
    public void update(Observable o, Object arg) {
        /* REALIZA LA CONÉXION A LA BD */
        if( ! conectado() ) {
            return;
        }
        boolean actualizado = this.ejecutarQuery(arg.toString());
        if(actualizado) {
            System.out.println("----------------------------------------------");
            System.out.println("Sentencia ejecutada:\n" + arg.toString());
        }
        cerrarConexion();
    }
    
}

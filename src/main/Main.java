package main;

import ventanas.Principal;

/**
 * Clase principal que descarga los datos de la BD, los guarda en listas en
 * memoria del programa e inicializa la interfaz del usuario
 * @author Ronaldhg
 */
public class Main {
    
    public static SQLite sqlite;
    
    public static void main(String[] args) {
        /* SUBIR DE PROLOG A SQLITE */
        //exportarHechosA_SQLite();
        
        /* ESTABLECE LA CONEXION A LA BASE DE DATOS */
        sqlite = new SQLite();
        if( ! sqlite.conectado() ) {
            return;
        }
        
        /* ELIMINA TODOS LOS REGISTROS DE LA BASE DE DATOS */
        //sqlite.deleteAll();
        
        /* IMPORTAR LOS REGISTROS DE SQLITE A LISTAS EN JAVA */
        sqlite.descargarDatos();
        sqlite.cerrarConexion();
        
        /* MEJORA EL ASPECTO DE LA VENTANA */
        //personalizarVentana();
        
        /* INICIA LA VENTANA PRINCIPAL DEL PROGRAMA */
        Principal menu = new Principal();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
    
    
    /**
     * Función que toma los hechos de prolog y los sube a la BD
     * Solo se usa una única vez
     */
    private static void exportarHechosA_SQLite() {
        Prolog p = new Prolog();
        /* CONSULTA LAS REGLAS DEL ARCHIVO PL */
        if( ! p.consultarQuery() ) {
            return;
        }
        p.exportarHechosA_SQLite();
        p.cerrarQuery();
    }
    
    /* MEJORA EL ASPECTO DE LAS VENTANAS */
    private static void personalizarVentana() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}

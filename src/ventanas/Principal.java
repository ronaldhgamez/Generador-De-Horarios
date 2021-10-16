package ventanas;

import java.awt.Color;
import java.util.ArrayList;
import main.*;

/**
 * VENTANA PRINCIPAL DE LA APLICACIÓN
 * @author Ronaldhg
 */
public class Principal extends javax.swing.JFrame {
    
    public Prolog prolog;
    public ArrayList<Color> colores = new ArrayList<>();
    
    /* BANDERAS PARA EN CASO DE MODIFICAR LOS DATOS QUE SE ACTUALICEN LOS PANELS */
    public boolean isInicializeB   = false;  // B: BLOQUE DE COMPUTACIÓN
    public boolean isInicializeAdm = false;  // ADMINISTRAR CURSOS
    public boolean isInicializeP   = false;  // P: PANEL PROFESORES
    public boolean isInicializeA   = false;  // A: PANEL AULAS
    public boolean isInicialize2   = false;  // CONSULTA 2
    public boolean isInicialize5   = false;  // CONSULTA 5
    public boolean isInicialize4   = false;  // CONSULTA 4
    
    /* TAMAÑO DE LOS PANELES */
    private final int ancho = 1130;
    private final int alto = 532;

    public Principal() {
        this.prolog = new Prolog();
        this.colores = new ArrayList<>();
        this.colores.add(new Color(255, 153, 153)); // rojo
        this.colores.add(new Color(204, 204, 255)); // celeste
        this.colores.add(new Color(255, 255, 102)); // amarillo
        this.colores.add(new Color(153, 255, 102)); // verde
        this.colores.add(new Color(204, 204, 204)); // gris
        this.colores.add(new Color(0, 204, 204));   // cian
        this.colores.add(new Color(255, 204, 204)); // papaya
        this.colores.add(new Color(153, 153, 255)); // azul
        this.colores.add(new Color(255, 204, 102)); // orange
        this.colores.add(new Color(204, 255, 153)); // verde2
        this.colores.add(Color.WHITE);
        initComponents();
        mostrarCursos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbed1 = new javax.swing.JTabbedPane();
        jPanelCursos = new javax.swing.JPanel();
        jTabbedPanelCursos = new javax.swing.JTabbedPane();
        jPanelBloque = new javax.swing.JPanel();
        jPanelAdministrar = new javax.swing.JPanel();
        jPanelProfesores = new javax.swing.JPanel();
        jPanelAulas = new javax.swing.JPanel();
        jPanelConsulta2 = new javax.swing.JPanel();
        jPanelConsulta4 = new javax.swing.JPanel();
        jPanelConsulta5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbed1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTabbed1.setMinimumSize(new java.awt.Dimension(200, 200));
        jTabbed1.setPreferredSize(new java.awt.Dimension(1130, 532));
        jTabbed1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbed1StateChanged(evt);
            }
        });

        jTabbedPanelCursos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPanelCursosStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBloqueLayout = new javax.swing.GroupLayout(jPanelBloque);
        jPanelBloque.setLayout(jPanelBloqueLayout);
        jPanelBloqueLayout.setHorizontalGroup(
            jPanelBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1133, Short.MAX_VALUE)
        );
        jPanelBloqueLayout.setVerticalGroup(
            jPanelBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jTabbedPanelCursos.addTab("Bloque de Ingeniería en Computación", jPanelBloque);

        javax.swing.GroupLayout jPanelAdministrarLayout = new javax.swing.GroupLayout(jPanelAdministrar);
        jPanelAdministrar.setLayout(jPanelAdministrarLayout);
        jPanelAdministrarLayout.setHorizontalGroup(
            jPanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1133, Short.MAX_VALUE)
        );
        jPanelAdministrarLayout.setVerticalGroup(
            jPanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jTabbedPanelCursos.addTab("Administrar Cursos", jPanelAdministrar);

        javax.swing.GroupLayout jPanelCursosLayout = new javax.swing.GroupLayout(jPanelCursos);
        jPanelCursos.setLayout(jPanelCursosLayout);
        jPanelCursosLayout.setHorizontalGroup(
            jPanelCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPanelCursos)
        );
        jPanelCursosLayout.setVerticalGroup(
            jPanelCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPanelCursos)
        );

        jTabbed1.addTab("Cursos", jPanelCursos);

        jPanelProfesores.setLayout(null);
        jTabbed1.addTab("Profesores", jPanelProfesores);

        javax.swing.GroupLayout jPanelAulasLayout = new javax.swing.GroupLayout(jPanelAulas);
        jPanelAulas.setLayout(jPanelAulasLayout);
        jPanelAulasLayout.setHorizontalGroup(
            jPanelAulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanelAulasLayout.setVerticalGroup(
            jPanelAulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jTabbed1.addTab("Aulas", jPanelAulas);

        javax.swing.GroupLayout jPanelConsulta2Layout = new javax.swing.GroupLayout(jPanelConsulta2);
        jPanelConsulta2.setLayout(jPanelConsulta2Layout);
        jPanelConsulta2Layout.setHorizontalGroup(
            jPanelConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanelConsulta2Layout.setVerticalGroup(
            jPanelConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jTabbed1.addTab("Consulta 2", jPanelConsulta2);

        javax.swing.GroupLayout jPanelConsulta4Layout = new javax.swing.GroupLayout(jPanelConsulta4);
        jPanelConsulta4.setLayout(jPanelConsulta4Layout);
        jPanelConsulta4Layout.setHorizontalGroup(
            jPanelConsulta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanelConsulta4Layout.setVerticalGroup(
            jPanelConsulta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jTabbed1.addTab("Consulta 4", jPanelConsulta4);

        jPanelConsulta5.setPreferredSize(new java.awt.Dimension(1065, 560));

        javax.swing.GroupLayout jPanelConsulta5Layout = new javax.swing.GroupLayout(jPanelConsulta5);
        jPanelConsulta5.setLayout(jPanelConsulta5Layout);
        jPanelConsulta5Layout.setHorizontalGroup(
            jPanelConsulta5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanelConsulta5Layout.setVerticalGroup(
            jPanelConsulta5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jTabbed1.addTab("Consulta5", jPanelConsulta5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTabbed1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbed1StateChanged
        if (jPanelProfesores.isShowing()) {
            if( ! isInicializeP ) {
                JPanelProfesores ventana = new JPanelProfesores(this);
                ventana.setSize(ancho, alto);
                ventana.setLocation(0,0);
                jPanelProfesores.removeAll();
                jPanelProfesores.add(ventana);
                jPanelProfesores.revalidate();
                jPanelProfesores.repaint();
                isInicializeP = true;
            }
        } else if (jPanelAulas.isShowing()) {
            if( ! isInicializeA ) {
                JPanelAulas ventana = new JPanelAulas(this);
                ventana.setSize(ancho, alto);
                ventana.setLocation(0,0);
                jPanelAulas.removeAll();
                jPanelAulas.add(ventana);
                jPanelAulas.revalidate();
                jPanelAulas.repaint();
                isInicializeA = true;
            }
        } else if (jPanelConsulta2.isShowing()) {
            if( ! isInicialize2 ) {
                Consulta2 ventana = new Consulta2(this);
                ventana.setSize(ancho, alto);
                ventana.setLocation(0,0);
                jPanelConsulta2.removeAll();
                jPanelConsulta2.add(ventana);
                jPanelConsulta2.revalidate();
                jPanelConsulta2.repaint();
                isInicialize2 = true;
            }
        } else if(jPanelConsulta4.isShowing()) {
            if( ! isInicialize4 ) {
                Consulta4 ventana = new Consulta4(this);
                ventana.setSize(ancho, alto);
                ventana.setLocation(0,0);
                jPanelConsulta4.removeAll();
                jPanelConsulta4.add(ventana);
                jPanelConsulta4.revalidate();
                jPanelConsulta4.repaint();
                isInicialize4 = true;
            }
        } else if(jPanelConsulta5.isShowing()) {
            if( ! isInicialize5 ) {
                Consulta5 ventana = new Consulta5(this);
                ventana.setSize(ancho, alto);
                ventana.setLocation(0,0);
                jPanelConsulta5.removeAll();
                jPanelConsulta5.add(ventana);
                jPanelConsulta5.revalidate();
                jPanelConsulta5.repaint();
                isInicialize5 = true;
            }
        }
    }//GEN-LAST:event_jTabbed1StateChanged

    private JPanelCursos panelCursos;
    private void mostrarCursos() {
        panelCursos = new JPanelCursos();
        panelCursos.setSize(1130, 532);
        panelCursos.setLocation(0,0);
        jPanelBloque.removeAll();
        jPanelBloque.add(panelCursos);
        jPanelBloque.revalidate();
        jPanelBloque.repaint();
        isInicializeB = true;
    }
    
    private void jTabbedPanelCursosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPanelCursosStateChanged
        if (jPanelBloque.isShowing()) {
            if( ! isInicializeB ) {
                mostrarCursos();
            }
        } else if(jPanelAdministrar.isShowing()) {
            if( ! isInicializeAdm ) {
                AdministrarCursos ventana = new AdministrarCursos(this);
                ventana.setSize(1130, 532);
                ventana.setLocation(0,0);
                jPanelAdministrar.removeAll();
                jPanelAdministrar.add(ventana);
                jPanelAdministrar.revalidate();
                jPanelAdministrar.repaint();
                isInicializeAdm = true;
            }
        }
    }//GEN-LAST:event_jTabbedPanelCursosStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelAdministrar;
    private javax.swing.JPanel jPanelAulas;
    private javax.swing.JPanel jPanelBloque;
    private javax.swing.JPanel jPanelConsulta2;
    private javax.swing.JPanel jPanelConsulta4;
    private javax.swing.JPanel jPanelConsulta5;
    protected javax.swing.JPanel jPanelCursos;
    private javax.swing.JPanel jPanelProfesores;
    private javax.swing.JTabbedPane jTabbed1;
    private javax.swing.JTabbedPane jTabbedPanelCursos;
    // End of variables declaration//GEN-END:variables
}

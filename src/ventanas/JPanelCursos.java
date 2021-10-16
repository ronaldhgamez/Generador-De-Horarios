package ventanas;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import main.JTextCurso;
import main.Listas;
import models.Curso;

/**
 *
 * @author Ronaldhg
 */
public class JPanelCursos extends javax.swing.JPanel {

    public ArrayList<JTextCurso> lista;
    
    public JPanelCursos() {
        this.lista = new ArrayList<>();
        initComponents();
        crearJTextCursos();
    }
    
    private void crearJTextCursos() {
        /* TAM TEXT AREAS */
        int largo = 130;
        int ancho = 60;//54;
        
        /* DISTANCIA ENTRE BOTONES */
        int dist = 64;
        
        int sem1 = 0, sem2 = 0, sem3 = 0, sem4 = 0;
        int sem5 = 0, sem6 = 0, sem7 = 0, sem8 = 0;
        
        ArrayList<Curso> cursos = Listas.getInstance().cursos;
        Color color = new Color(102, 255, 0);
        for(Curso c : cursos) {
            String text = c.getIdCurso() + '\t' + "cr " + c.getCreditos() 
                    + '\n' + c.getNombre() + "\nClase(s) " + c.getClasesXSem()
                    + "\nTipo: " + c.getTipo();
            
            JTextArea nuevo = new JTextArea(text, 4, 8);
            nuevo.setFont(new java.awt.Font("Arial", 0, 11));
            nuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            nuevo.setEditable(false);
            nuevo.setBackground(color);
            switch (c.getSemestre()) {
                case 1:
                    nuevo.setBounds(0, sem1, largo, ancho);
                    sem1 += dist;
                    jPanelSem1.add(nuevo);
                    break;
                case 2:
                    nuevo.setBounds(0, sem2, largo, ancho);
                    sem2 += dist;
                    jPanelSem2.add(nuevo);
                    break;
                case 3:
                    nuevo.setBounds(0, sem3, largo, ancho);
                    sem3 += dist;
                    jPanelSem3.add(nuevo);
                    break;
                case 4:
                    nuevo.setBounds(0, sem4, largo, ancho);
                    sem4 += dist;
                    jPanelSem4.add(nuevo);
                    break;
                case 5:
                    nuevo.setBounds(0, sem5, largo, ancho);
                    sem5 += dist;
                    jPanelSem5.add(nuevo);
                    break;
                case 6:
                    nuevo.setBounds(0, sem6, largo, ancho);
                    sem6 += dist;
                    jPanelSem6.add(nuevo);
                    break;
                case 7:
                    nuevo.setBounds(0, sem7, largo, ancho);
                    sem7 += dist;
                    jPanelSem7.add(nuevo);
                    break;
                default:
                    nuevo.setBounds(0, sem8, largo, ancho);
                    sem8 += dist;
                    jPanelSem8.add(nuevo);
                    break;
            }
        }
        actualizarPanels();
    }
    
    private void actualizarPanels() {
        this.jPanelSem1.updateUI();
        this.jPanelSem2.updateUI();
        this.jPanelSem3.updateUI();
        this.jPanelSem4.updateUI();
        this.jPanelSem5.updateUI();
        this.jPanelSem6.updateUI();
        this.jPanelSem7.updateUI();
        this.jPanelSem8.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanelSem2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelSem3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanelSem4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanelSem1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanelSem5 = new javax.swing.JPanel();
        jPanelSem7 = new javax.swing.JPanel();
        jPanelSem8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelSem6 = new javax.swing.JPanel();

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Semestre 4");

        javax.swing.GroupLayout jPanelSem2Layout = new javax.swing.GroupLayout(jPanelSem2);
        jPanelSem2.setLayout(jPanelSem2Layout);
        jPanelSem2Layout.setHorizontalGroup(
            jPanelSem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem2Layout.setVerticalGroup(
            jPanelSem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Semestre 5");

        javax.swing.GroupLayout jPanelSem3Layout = new javax.swing.GroupLayout(jPanelSem3);
        jPanelSem3.setLayout(jPanelSem3Layout);
        jPanelSem3Layout.setHorizontalGroup(
            jPanelSem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem3Layout.setVerticalGroup(
            jPanelSem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Semestre 6");

        javax.swing.GroupLayout jPanelSem4Layout = new javax.swing.GroupLayout(jPanelSem4);
        jPanelSem4.setLayout(jPanelSem4Layout);
        jPanelSem4Layout.setHorizontalGroup(
            jPanelSem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem4Layout.setVerticalGroup(
            jPanelSem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Semestre 7");

        javax.swing.GroupLayout jPanelSem1Layout = new javax.swing.GroupLayout(jPanelSem1);
        jPanelSem1.setLayout(jPanelSem1Layout);
        jPanelSem1Layout.setHorizontalGroup(
            jPanelSem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem1Layout.setVerticalGroup(
            jPanelSem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Semestre 8");

        javax.swing.GroupLayout jPanelSem5Layout = new javax.swing.GroupLayout(jPanelSem5);
        jPanelSem5.setLayout(jPanelSem5Layout);
        jPanelSem5Layout.setHorizontalGroup(
            jPanelSem5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem5Layout.setVerticalGroup(
            jPanelSem5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelSem7Layout = new javax.swing.GroupLayout(jPanelSem7);
        jPanelSem7.setLayout(jPanelSem7Layout);
        jPanelSem7Layout.setHorizontalGroup(
            jPanelSem7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem7Layout.setVerticalGroup(
            jPanelSem7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelSem8Layout = new javax.swing.GroupLayout(jPanelSem8);
        jPanelSem8.setLayout(jPanelSem8Layout);
        jPanelSem8Layout.setHorizontalGroup(
            jPanelSem8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem8Layout.setVerticalGroup(
            jPanelSem8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Semestre 1");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Semestre 2");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Semestre 3");

        javax.swing.GroupLayout jPanelSem6Layout = new javax.swing.GroupLayout(jPanelSem6);
        jPanelSem6.setLayout(jPanelSem6Layout);
        jPanelSem6Layout.setHorizontalGroup(
            jPanelSem6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanelSem6Layout.setVerticalGroup(
            jPanelSem6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelSem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelSem8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSem8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanelSem1;
    private javax.swing.JPanel jPanelSem2;
    private javax.swing.JPanel jPanelSem3;
    private javax.swing.JPanel jPanelSem4;
    private javax.swing.JPanel jPanelSem5;
    private javax.swing.JPanel jPanelSem6;
    private javax.swing.JPanel jPanelSem7;
    private javax.swing.JPanel jPanelSem8;
    // End of variables declaration//GEN-END:variables
}

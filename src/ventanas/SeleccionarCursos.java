package ventanas;

import main.JTextCurso;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.Listas;
import models.Curso;

/**
 *
 * @author Ronaldhg
 */
public class SeleccionarCursos extends javax.swing.JFrame {
    
    public ArrayList<JTextCurso> lista;
    public Consulta4 previo;

    public SeleccionarCursos(Consulta4 previo) {
        this.lista = new ArrayList<>();
        this.previo = previo;
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
        Color color = Color.white;
        
        for(Curso c : cursos) {
            String text = c.getIdCurso() + "\n" + c.getNombre() + "\n" 
                    + c.getCreditos() + " cr" + "\n" + c.getClasesXSem() + " clase(s)";
            JTextCurso nuevo = new JTextCurso(c.getIdCurso(), text, c.getSemestre(), 4, 8);
            nuevo.setFont(new java.awt.Font("Arial", 0, 11));
            nuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            nuevo.setEditable(false);
            nuevo.addMouseListener(nuevo);
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
            this.lista.add(nuevo);
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

    private String getSeleccionados() {
        String curso = "";
        int tam = lista.size();
        for (int i = 0; i < tam; i++) {
            if (lista.get(i).isSelected()) {
                curso += '\'' + lista.get(i).getIdCurso() + '\'';
                curso += ", ";
            }
        }
        if(curso.equals(""))
            return "";
        // QUITA LA ULTIMA COMA
        curso = curso.substring(0, (curso.length() - 2));
        return curso;
    }
    
    private void cerrar() {
        this.lista = null;
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancelar = new javax.swing.JButton();
        jButtonFinalizar = new javax.swing.JButton();
        jTextFieldCombinaciones = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButtonReiniciar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(1147, 580, 580, 580));
        setMinimumSize(new java.awt.Dimension(1055, 560));
        setPreferredSize(new java.awt.Dimension(1147, 580));
        setSize(new java.awt.Dimension(1147, 580));
        getContentPane().setLayout(null);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancelar);
        jButtonCancelar.setBounds(1000, 10, 150, 30);

        jButtonFinalizar.setText("Generar Horarios");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFinalizar);
        jButtonFinalizar.setBounds(340, 10, 140, 30);

        jTextFieldCombinaciones.setText("100");
        getContentPane().add(jTextFieldCombinaciones);
        jTextFieldCombinaciones.setBounds(220, 10, 75, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Combinaciones:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(90, 20, 150, 17);

        jButtonReiniciar.setText("Reiniciar");
        jButtonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReiniciar);
        jButtonReiniciar.setBounds(520, 10, 130, 30);
        getContentPane().add(jLabel10);
        jLabel10.setBounds(180, 550, 0, 0);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Semestre 4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(470, 50, 80, 15);

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

        getContentPane().add(jPanelSem2);
        jPanelSem2.setBounds(160, 70, 130, 490);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Semestre 5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(610, 50, 80, 15);

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

        getContentPane().add(jPanelSem3);
        jPanelSem3.setBounds(300, 70, 130, 490);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Semestre 6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(740, 50, 80, 15);

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

        getContentPane().add(jPanelSem4);
        jPanelSem4.setBounds(440, 70, 130, 490);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Semestre 7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(890, 50, 80, 15);

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

        getContentPane().add(jPanelSem1);
        jPanelSem1.setBounds(20, 70, 130, 490);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Semestre 8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(1020, 50, 90, 15);

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

        getContentPane().add(jPanelSem5);
        jPanelSem5.setBounds(580, 70, 130, 490);

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

        getContentPane().add(jPanelSem7);
        jPanelSem7.setBounds(860, 70, 130, 490);

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

        getContentPane().add(jPanelSem8);
        jPanelSem8.setBounds(1000, 70, 130, 490);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Semestre 1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 50, 80, 15);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Semestre 2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(190, 50, 80, 15);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Semestre 3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(330, 50, 80, 15);

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

        getContentPane().add(jPanelSem6);
        jPanelSem6.setBounds(720, 70, 130, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        cerrar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        try {
            int totCom = Integer.parseInt(jTextFieldCombinaciones.getText());
            if(totCom <= 0) {
                JOptionPane.showMessageDialog(this, "Deber ser mayor",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String cursos = getSeleccionados();
            if(cursos.equals("")) {
                JOptionPane.showMessageDialog(this, "Seleccione al menos un curso",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            /* CIERRA ESTA VENTANA */
            cerrar();
            /* REALIZA LA CONSULTA */
            previo.consulta4(cursos, totCom);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar cuantas combinaciones desea",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonFinalizarActionPerformed

    private void jButtonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarActionPerformed
        Color gris = Color.WHITE;
        for (JTextCurso j : lista) {
            if(j.getBackground() != gris) {
                j.setBackground(gris);
                j.setSelected(false);
            }
        }
    }//GEN-LAST:event_jButtonReiniciarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonReiniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelSem1;
    private javax.swing.JPanel jPanelSem2;
    private javax.swing.JPanel jPanelSem3;
    private javax.swing.JPanel jPanelSem4;
    private javax.swing.JPanel jPanelSem5;
    private javax.swing.JPanel jPanelSem6;
    private javax.swing.JPanel jPanelSem7;
    private javax.swing.JPanel jPanelSem8;
    private javax.swing.JTextField jTextFieldCombinaciones;
    // End of variables declaration//GEN-END:variables
}

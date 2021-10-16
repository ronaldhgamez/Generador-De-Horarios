package ventanas;

import javax.swing.JOptionPane;
import main.Listas;
import models.*;

/**
 * VENTANA PARA ADMINISTRAR LOS CURSOS (AGREGAR, ELIMINAR, EDITAR)
 * @author Ronaldhg
 */
public class AdministrarCursos extends javax.swing.JPanel {

    public Listas lista;
    // GUARDA TEMPORALMENTE EL ID DEL CURSO SELECCIONADO
    private String idCurso;
    private Principal pr;
    
    public AdministrarCursos(Principal pr) {
        this.lista = Listas.getInstance();
        this.idCurso = null;
        this.pr = pr;
        initComponents();
        cargarComboBox();
    }

    private void cargarComboBox() {
        jComboBoxCursos.removeAllItems();
        jComboBoxCursos.addItem("Seleccione el curso");
        for (Curso c : lista.cursos) {
            jComboBoxCursos.addItem(c.getIdCurso() +  ", " + c.getNombre());
        }
    }
    
    /**
     * NOTIFICA A PROLOG Y A LAS VENTANAS DE CONSULTA QUE LA INFORMACIÓN DE LAS
     * LISTAS FUE MODIFICADA, PARA EVITAR CONFLICTOS CON LAS CONSULTAS Y EL
     * DESPLIEGUE DE LOS DATOS SE ACTUALICE
     */
    private void notificarModificaciones() {
        // Para actualizar la información en las otras ventanas
        pr.isInicializeB = false; // Panel de cursos
        pr.isInicializeP = false; // Panel de profesores
        // Paneles de consulta se reiniciarán también
        pr.isInicialize2 = false;
        pr.isInicialize4 = false;
        pr.isInicialize5 = false;
        /* CAMBIA EL BOOLEANO DE LA VENTANA PRINCIPAL */
        lista.fueModificado = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxCursos = new javax.swing.JComboBox<>();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        jTextFieldIdCodigo = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldCreditos1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxCXS1 = new javax.swing.JComboBox<>();
        jComboBoxSemestre1 = new javax.swing.JComboBox<>();
        jComboBoxTipo1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldCreditos = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jComboBoxCXS = new javax.swing.JComboBox<>();
        jComboBoxSemestre = new javax.swing.JComboBox<>();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(990, 560));
        setName(""); // NOI18N
        setLayout(null);

        jComboBoxCursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el curso" }));
        jComboBoxCursos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCursosItemStateChanged(evt);
            }
        });
        add(jComboBoxCursos);
        jComboBoxCursos.setBounds(70, 80, 390, 30);

        jButtonEditar.setText("Editar");
        jButtonEditar.setEnabled(false);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        add(jButtonEditar);
        jButtonEditar.setBounds(70, 370, 130, 50);

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        add(jButtonEliminar);
        jButtonEliminar.setBounds(350, 370, 110, 50);

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        add(jButtonRegistrar);
        jButtonRegistrar.setBounds(580, 360, 190, 50);

        jTextFieldIdCodigo.setEditable(false);
        add(jTextFieldIdCodigo);
        jTextFieldIdCodigo.setBounds(220, 130, 240, 30);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setEnabled(false);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        add(jButtonGuardar);
        jButtonGuardar.setBounds(210, 370, 130, 50);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setText("Información de Cursos");
        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel13);
        jLabel13.setBounds(70, 40, 390, 30);
        add(jTextFieldNombre1);
        jTextFieldNombre1.setBounds(750, 120, 230, 30);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Nombre");
        add(jLabel15);
        jLabel15.setBounds(580, 120, 110, 17);
        add(jTextFieldCreditos1);
        jTextFieldCreditos1.setBounds(750, 160, 230, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Créditos");
        add(jLabel16);
        jLabel16.setBounds(580, 160, 110, 17);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Semestre");
        add(jLabel17);
        jLabel17.setBounds(580, 200, 90, 17);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Clases a la semana");
        add(jLabel18);
        jLabel18.setBounds(580, 280, 140, 17);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Tipo");
        add(jLabel19);
        jLabel19.setBounds(580, 240, 80, 17);

        jComboBoxCXS1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "total", "1", "2" }));
        add(jComboBoxCXS1);
        jComboBoxCXS1.setBounds(750, 280, 110, 30);

        jComboBoxSemestre1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "semestre", "1", "2", "3", "4", "5", "6", "7", "8" }));
        add(jComboBoxSemestre1);
        jComboBoxSemestre1.setBounds(750, 200, 110, 30);

        jComboBoxTipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "tipo", "nor", "lab" }));
        add(jComboBoxTipo1);
        jComboBoxTipo1.setBounds(750, 240, 110, 30);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setText("Registrar curso");
        jLabel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel14);
        jLabel14.setBounds(580, 40, 410, 30);

        jTextFieldNombre.setEditable(false);
        add(jTextFieldNombre);
        jTextFieldNombre.setBounds(220, 170, 240, 30);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Código");
        add(jLabel20);
        jLabel20.setBounds(70, 130, 110, 17);

        jTextFieldCreditos.setEditable(false);
        add(jTextFieldCreditos);
        jTextFieldCreditos.setBounds(220, 210, 240, 30);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Créditos");
        add(jLabel21);
        jLabel21.setBounds(70, 210, 110, 17);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Semestre");
        add(jLabel22);
        jLabel22.setBounds(70, 250, 90, 17);

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Clases a la semana");
        add(jLabel23);
        jLabel23.setBounds(70, 330, 140, 17);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Tipo");
        add(jLabel24);
        jLabel24.setBounds(70, 290, 80, 17);

        jComboBoxCXS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "total", "1", "2" }));
        add(jComboBoxCXS);
        jComboBoxCXS.setBounds(220, 330, 110, 30);

        jComboBoxSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "semestre", "1", "2", "3", "4", "5", "6", "7", "8" }));
        add(jComboBoxSemestre);
        jComboBoxSemestre.setBounds(220, 250, 110, 30);

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "tipo", "nor", "lab" }));
        add(jComboBoxTipo);
        jComboBoxTipo.setBounds(220, 290, 110, 30);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Nombre");
        add(jLabel25);
        jLabel25.setBounds(70, 170, 110, 17);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCursosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCursosItemStateChanged
        try {
            this.jTextFieldNombre.setEditable(false);
            this.jTextFieldCreditos.setEditable(false);
            this.jButtonGuardar.setEnabled(false);
            
            if( ! jComboBoxCursos.getSelectedItem().toString().equals("Seleccione el curso") ) {
                String select = jComboBoxCursos.getSelectedItem().toString();
                String id = select.split(",")[0];
                Curso curso = lista.buscarCurso(id);
                if(curso != null) {
                    this.jTextFieldIdCodigo.setText(curso.getIdCurso());
                    this.jTextFieldNombre.setText(curso.getNombre());
                    this.jTextFieldCreditos.setText(curso.getCreditos() + "");
                    this.jComboBoxSemestre.setSelectedItem(curso.getSemestre() + "");
                    this.jComboBoxTipo.setSelectedItem(curso.getTipo());
                    this.jComboBoxCXS.setSelectedItem(curso.getClasesXSem() + "");
                    this.idCurso = curso.getIdCurso();
                    this.jButtonEditar.setEnabled(true);
                    this.jButtonEliminar.setEnabled(true);
                }
            } else {
                this.jTextFieldIdCodigo.setText("");
                this.jTextFieldNombre.setText("");
                this.jTextFieldCreditos.setText("");
                this.jComboBoxSemestre.setSelectedIndex(0);
                this.jComboBoxTipo.setSelectedIndex(0);
                this.jComboBoxCXS.setSelectedIndex(0);
                this.jButtonEditar.setEnabled(false);
                this.jButtonEliminar.setEnabled(false);
                this.idCurso = null;
            }
        } catch (NullPointerException e) {}
    }//GEN-LAST:event_jComboBoxCursosItemStateChanged

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        this.jTextFieldNombre.setEditable(true);
        this.jTextFieldCreditos.setEditable(true);
        this.jButtonGuardar.setEnabled(true);
        this.jButtonEliminar.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        boolean estaEliminado = lista.borrarCurso(idCurso);
        if(estaEliminado) {
            cargarComboBox();
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        if(jComboBoxSemestre1.getSelectedItem().toString().equals("semestre")) {
            JOptionPane.showMessageDialog(this, "Seleccione un semestre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(jComboBoxTipo1.getSelectedItem().toString().equals("tipo")) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de curso", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // EXTRAE LA INFORMACIÓN
        int cr, sem, cxs;
        try {
            cr = Integer.parseInt(jTextFieldCreditos1.getText());
            sem = Integer.parseInt(jComboBoxSemestre1.getSelectedItem().toString());
            cxs = Integer.parseInt(jComboBoxCXS1.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = jTextFieldNombre1.getText();
        String tip = jComboBoxTipo1.getSelectedItem().toString();
        
        boolean insertado = lista.insertarCurso(nombre, cr, sem, tip, cxs);
        if(insertado) {
            this.jTextFieldNombre1.setText("");
            this.jTextFieldCreditos1.setText("");
            this.jComboBoxSemestre1.setSelectedIndex(0);
            this.jComboBoxTipo1.setSelectedIndex(0);
            this.jComboBoxCXS1.setSelectedIndex(0);
            cargarComboBox();
            // Para actualizar la vista de los cursos
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if(jComboBoxSemestre.getSelectedItem().toString().equals("semestre")) {
            JOptionPane.showMessageDialog(this, "Seleccione un semestre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(jComboBoxTipo.getSelectedItem().toString().equals("tipo")) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de curso", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // EXTRAE LA INFORMACIÓN
        int cr, sem, cxs;
        try {
            cr = Integer.parseInt(jTextFieldCreditos.getText());
            sem = Integer.parseInt(jComboBoxSemestre.getSelectedItem().toString());
            cxs = Integer.parseInt(jComboBoxCXS.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = jTextFieldNombre.getText();
        String tip = jComboBoxTipo.getSelectedItem().toString();
        
        boolean editado = lista.editarCurso(idCurso, nombre, cr, sem, tip, cxs);
        if(editado) {
            this.jTextFieldNombre.setEditable(false);
            this.jTextFieldCreditos.setEditable(false);
            this.jButtonGuardar.setEnabled(false);
            cargarComboBox();
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JComboBox<String> jComboBoxCXS;
    private javax.swing.JComboBox<String> jComboBoxCXS1;
    private javax.swing.JComboBox<String> jComboBoxCursos;
    private javax.swing.JComboBox<String> jComboBoxSemestre;
    private javax.swing.JComboBox<String> jComboBoxSemestre1;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JComboBox<String> jComboBoxTipo1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JTextField jTextFieldCreditos;
    private javax.swing.JTextField jTextFieldCreditos1;
    private javax.swing.JTextField jTextFieldIdCodigo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre1;
    // End of variables declaration//GEN-END:variables
}

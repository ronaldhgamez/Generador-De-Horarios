package ventanas;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import main.*;
import models.*;

/**
 *
 * @author Ronaldhg
 */
public class JPanelProfesores extends javax.swing.JPanel {

    public Principal pr;
    public Listas lista;
    // Guarda temporalmente la cedula del profesor a modificar
    private String cedula;
    
    public JPanelProfesores(Principal pr) {
        this.pr = pr;
        this.lista = Listas.getInstance();
        this.cedula = null;
        initComponents();
        cargarComboBox();
    }

    private void cargarComboBox() {
        limpiarHorario();
        jListCursos.setModel(new DefaultListModel());
        jComboBoxProfesores.removeAllItems();
        jComboBoxProfesores.addItem("Seleccione el profesor");
        for (Profesor p : lista.profesores) {
            jComboBoxProfesores.addItem(p.getCedula()+  ", "+ p.getNombre());
        }
    }
    
    private void mostrarHorario() {
        limpiarHorario();
        ArrayList<Disponible> aux = lista.disponible;
        for (Disponible i : aux) {
            if(i.getCedula().equals(cedula)) {
                desplegar(i.getDia(), String.valueOf(i.getHoraIni()));
            }
        }
    }
    
    private void mostrarCursosQueDa() {
        DefaultListModel list = lista.cursosQueImparte(cedula);
        jListCursos.setModel(list);
    }
    
    private void desplegar(String dia, String horaIni) {
        Color color = Color.GREEN;
        switch (dia) {
                case "L":
                    if (horaIni.equals("7"))
                        jTextAreaL7.setBackground(color);
                    else if (horaIni.equals("9"))
                        jTextAreaL9.setBackground(color);
                    else if (horaIni.equals("12"))
                        jTextAreaL12.setBackground(color);
                    else
                        jTextAreaL14.setBackground(color);
                    break;
                case "K":
                    if (horaIni.equals("7"))
                        jTextAreaK7.setBackground(color);
                    else if (horaIni.equals("9"))
                        jTextAreaK9.setBackground(color);
                    else if (horaIni.equals("12"))
                        jTextAreaK12.setBackground(color);
                    else
                        jTextAreaK14.setBackground(color);
                    break;
                case "M":
                    if (horaIni.equals("7"))
                        jTextAreaM7.setBackground(color);
                    else if (horaIni.equals("9"))
                        jTextAreaM9.setBackground(color);
                    else if (horaIni.equals("12"))
                        jTextAreaM12.setBackground(color);
                    else
                        jTextAreaM14.setBackground(color);
                    break;
                case "J":
                    if (horaIni.equals("7"))
                        jTextAreaJ7.setBackground(color);
                    else if (horaIni.equals("9"))
                        jTextAreaJ9.setBackground(color);
                    else if (horaIni.equals("12"))
                        jTextAreaJ12.setBackground(color);
                    else
                        jTextAreaJ14.setBackground(color);
                    break;
                default:
                    if (horaIni.equals("7"))
                        jTextAreaV7.setBackground(color);
                    else if (horaIni.equals("9"))
                        jTextAreaV9.setBackground(color);
                    else if (horaIni.equals("12"))
                        jTextAreaV12.setBackground(color);
                    else
                        jTextAreaV14.setBackground(color);
                    break;
            }
    }
    
    /**
     * NOTIFICA A PROLOG Y A LAS VENTANAS DE CONSULTA QUE LA INFORMACIÓN DE LAS
     * LISTAS FUE MODIFICADA, PARA EVITAR CONFLICTOS CON LAS CONSULTAS Y EL
     * DESPLIEGUE DE LOS DATOS SE ACTUALICE
     */
    private void notificarModificaciones() {
        // Paneles de consulta se reiniciarán también
        pr.isInicialize2 = false;
        pr.isInicialize5 = false;
        /* CAMBIA EL BOOLEANO DE LA VENTANA PRINCIPAL */
        lista.fueModificado = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTextAreaL7 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaK7 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaM7 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaJ7 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaV7 = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaV9 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaJ9 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaM9 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaK9 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaL9 = new javax.swing.JTextArea();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextAreaL12 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextAreaK12 = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextAreaM12 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextAreaJ12 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextAreaV12 = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextAreaV14 = new javax.swing.JTextArea();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextAreaJ14 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextAreaM14 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextAreaK14 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextAreaL14 = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxProfesores = new javax.swing.JComboBox<>();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCedula = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jTextFieldCedula1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCursos = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jButtonAgregarCursoAProfe = new javax.swing.JButton();
        jButtonQuitarCurso = new javax.swing.JButton();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonAgregarHorario = new javax.swing.JButton();
        jButtonQuitarHorario = new javax.swing.JButton();
        jComboBoxHorario = new javax.swing.JComboBox<>();
        jComboBoxDia = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("    Hora    |             Lunes           |          Martes            |        Miércoles           |         Jueves             |            Viernes");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        add(jLabel2);
        jLabel2.setBounds(480, 70, 550, 17);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(1);
        jTextArea1.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea1.setRows(1);
        jTextArea1.setTabSize(1);
        jTextArea1.setText("\t09:15\n\t11:30");
        jTextArea1.setBorder(null);
        jTextArea1.setEnabled(false);
        jTextArea1.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane24.setViewportView(jTextArea1);

        add(jScrollPane24);
        jScrollPane24.setBounds(480, 130, 50, 40);

        jTextAreaL7.setColumns(1);
        jTextAreaL7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL7.setRows(2);
        jTextAreaL7.setTabSize(2);
        jTextAreaL7.setText("\n");
        jTextAreaL7.setBorder(null);
        jTextAreaL7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane23.setViewportView(jTextAreaL7);

        add(jScrollPane23);
        jScrollPane23.setBounds(530, 90, 100, 40);

        jTextAreaK7.setColumns(1);
        jTextAreaK7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK7.setRows(2);
        jTextAreaK7.setTabSize(2);
        jTextAreaK7.setBorder(null);
        jTextAreaK7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane5.setViewportView(jTextAreaK7);

        add(jScrollPane5);
        jScrollPane5.setBounds(630, 90, 100, 40);

        jTextAreaM7.setColumns(1);
        jTextAreaM7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM7.setRows(2);
        jTextAreaM7.setTabSize(2);
        jTextAreaM7.setBorder(null);
        jTextAreaM7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane6.setViewportView(jTextAreaM7);

        add(jScrollPane6);
        jScrollPane6.setBounds(730, 90, 100, 40);

        jTextAreaJ7.setColumns(1);
        jTextAreaJ7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ7.setRows(2);
        jTextAreaJ7.setTabSize(2);
        jTextAreaJ7.setBorder(null);
        jTextAreaJ7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane7.setViewportView(jTextAreaJ7);

        add(jScrollPane7);
        jScrollPane7.setBounds(830, 90, 100, 40);

        jTextAreaV7.setColumns(1);
        jTextAreaV7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV7.setRows(2);
        jTextAreaV7.setTabSize(2);
        jTextAreaV7.setBorder(null);
        jTextAreaV7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane4.setViewportView(jTextAreaV7);

        add(jScrollPane4);
        jScrollPane4.setBounds(930, 90, 100, 40);

        jTextAreaV9.setColumns(1);
        jTextAreaV9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV9.setRows(2);
        jTextAreaV9.setTabSize(2);
        jTextAreaV9.setBorder(null);
        jTextAreaV9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane12.setViewportView(jTextAreaV9);

        add(jScrollPane12);
        jScrollPane12.setBounds(930, 130, 100, 40);

        jTextAreaJ9.setColumns(1);
        jTextAreaJ9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ9.setRows(2);
        jTextAreaJ9.setTabSize(2);
        jTextAreaJ9.setBorder(null);
        jTextAreaJ9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane11.setViewportView(jTextAreaJ9);

        add(jScrollPane11);
        jScrollPane11.setBounds(830, 130, 100, 40);

        jTextAreaM9.setColumns(1);
        jTextAreaM9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM9.setRows(2);
        jTextAreaM9.setTabSize(2);
        jTextAreaM9.setBorder(null);
        jTextAreaM9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane10.setViewportView(jTextAreaM9);

        add(jScrollPane10);
        jScrollPane10.setBounds(730, 130, 100, 40);

        jTextAreaK9.setColumns(1);
        jTextAreaK9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK9.setRows(2);
        jTextAreaK9.setTabSize(2);
        jTextAreaK9.setBorder(null);
        jTextAreaK9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane9.setViewportView(jTextAreaK9);

        add(jScrollPane9);
        jScrollPane9.setBounds(630, 130, 100, 40);

        jTextAreaL9.setColumns(1);
        jTextAreaL9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL9.setRows(2);
        jTextAreaL9.setTabSize(2);
        jTextAreaL9.setText("\n");
        jTextAreaL9.setBorder(null);
        jTextAreaL9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane8.setViewportView(jTextAreaL9);

        add(jScrollPane8);
        jScrollPane8.setBounds(530, 130, 100, 40);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(1);
        jTextArea3.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea3.setRows(2);
        jTextArea3.setTabSize(1);
        jTextArea3.setText("\t12:30\n\t14:45");
        jTextArea3.setBorder(null);
        jTextArea3.setEnabled(false);
        jTextArea3.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane26.setViewportView(jTextArea3);

        add(jScrollPane26);
        jScrollPane26.setBounds(480, 180, 50, 40);

        jTextAreaL12.setColumns(1);
        jTextAreaL12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL12.setRows(2);
        jTextAreaL12.setTabSize(2);
        jTextAreaL12.setText("\n");
        jTextAreaL12.setBorder(null);
        jTextAreaL12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane13.setViewportView(jTextAreaL12);

        add(jScrollPane13);
        jScrollPane13.setBounds(530, 180, 100, 40);

        jTextAreaK12.setColumns(1);
        jTextAreaK12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK12.setRows(2);
        jTextAreaK12.setTabSize(2);
        jTextAreaK12.setBorder(null);
        jTextAreaK12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane14.setViewportView(jTextAreaK12);

        add(jScrollPane14);
        jScrollPane14.setBounds(630, 180, 100, 40);

        jTextAreaM12.setColumns(1);
        jTextAreaM12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM12.setRows(2);
        jTextAreaM12.setTabSize(2);
        jTextAreaM12.setBorder(null);
        jTextAreaM12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane15.setViewportView(jTextAreaM12);

        add(jScrollPane15);
        jScrollPane15.setBounds(730, 180, 100, 40);

        jTextAreaJ12.setColumns(1);
        jTextAreaJ12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ12.setRows(2);
        jTextAreaJ12.setTabSize(2);
        jTextAreaJ12.setBorder(null);
        jTextAreaJ12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane16.setViewportView(jTextAreaJ12);

        add(jScrollPane16);
        jScrollPane16.setBounds(830, 180, 100, 40);

        jTextAreaV12.setColumns(1);
        jTextAreaV12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV12.setRows(2);
        jTextAreaV12.setTabSize(2);
        jTextAreaV12.setBorder(null);
        jTextAreaV12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane17.setViewportView(jTextAreaV12);

        add(jScrollPane17);
        jScrollPane17.setBounds(930, 180, 100, 40);

        jTextAreaV14.setColumns(1);
        jTextAreaV14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV14.setRows(2);
        jTextAreaV14.setTabSize(2);
        jTextAreaV14.setBorder(null);
        jTextAreaV14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane22.setViewportView(jTextAreaV14);

        add(jScrollPane22);
        jScrollPane22.setBounds(930, 220, 100, 40);

        jTextAreaJ14.setColumns(1);
        jTextAreaJ14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ14.setRows(2);
        jTextAreaJ14.setTabSize(2);
        jTextAreaJ14.setBorder(null);
        jTextAreaJ14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane21.setViewportView(jTextAreaJ14);

        add(jScrollPane21);
        jScrollPane21.setBounds(830, 220, 100, 40);

        jTextAreaM14.setColumns(1);
        jTextAreaM14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM14.setRows(2);
        jTextAreaM14.setTabSize(2);
        jTextAreaM14.setBorder(null);
        jTextAreaM14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane20.setViewportView(jTextAreaM14);

        add(jScrollPane20);
        jScrollPane20.setBounds(730, 220, 100, 40);

        jTextAreaK14.setColumns(1);
        jTextAreaK14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK14.setRows(2);
        jTextAreaK14.setTabSize(2);
        jTextAreaK14.setBorder(null);
        jTextAreaK14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane19.setViewportView(jTextAreaK14);

        add(jScrollPane19);
        jScrollPane19.setBounds(630, 220, 100, 40);

        jTextAreaL14.setColumns(1);
        jTextAreaL14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL14.setRows(2);
        jTextAreaL14.setTabSize(2);
        jTextAreaL14.setText("\n");
        jTextAreaL14.setBorder(null);
        jTextAreaL14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane18.setViewportView(jTextAreaL14);

        add(jScrollPane18);
        jScrollPane18.setBounds(530, 220, 100, 40);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(1);
        jTextArea2.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea2.setRows(1);
        jTextArea2.setTabSize(1);
        jTextArea2.setText("\t07:00\n\t09:10");
        jTextArea2.setBorder(null);
        jTextArea2.setEnabled(false);
        jTextArea2.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane27.setViewportView(jTextArea2);

        add(jScrollPane27);
        jScrollPane27.setBounds(480, 90, 50, 40);

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(1);
        jTextArea5.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea5.setRows(2);
        jTextArea5.setTabSize(1);
        jTextArea5.setText("\t14:50\n\t16:05");
        jTextArea5.setBorder(null);
        jTextArea5.setEnabled(false);
        jTextArea5.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane28.setViewportView(jTextArea5);

        add(jScrollPane28);
        jScrollPane28.setBounds(480, 220, 50, 40);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Asignar cursos al profesor");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel1);
        jLabel1.setBounds(40, 200, 290, 30);

        jComboBoxProfesores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el profesor" }));
        jComboBoxProfesores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxProfesoresItemStateChanged(evt);
            }
        });
        add(jComboBoxProfesores);
        jComboBoxProfesores.setBounds(40, 30, 290, 30);

        jButtonEditar.setText("Editar");
        jButtonEditar.setEnabled(false);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        add(jButtonEditar);
        jButtonEditar.setBounds(40, 150, 90, 40);

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        add(jButtonEliminar);
        jButtonEliminar.setBounds(240, 150, 90, 40);

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        add(jButtonRegistrar);
        jButtonRegistrar.setBounds(220, 440, 110, 30);

        jTextFieldNombre.setEditable(false);
        add(jTextFieldNombre);
        jTextFieldNombre.setBounds(100, 70, 230, 30);

        jTextFieldCedula.setEditable(false);
        add(jTextFieldCedula);
        jTextFieldCedula.setBounds(100, 110, 230, 30);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setEnabled(false);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        add(jButtonGuardar);
        jButtonGuardar.setBounds(140, 150, 90, 40);

        jLabel5.setText("Nombre");
        add(jLabel5);
        jLabel5.setBounds(40, 370, 50, 20);

        jLabel6.setText("Cédula");
        add(jLabel6);
        jLabel6.setBounds(40, 410, 50, 14);
        add(jTextFieldNombre1);
        jTextFieldNombre1.setBounds(100, 360, 230, 30);
        add(jTextFieldCedula1);
        jTextFieldCedula1.setBounds(100, 400, 230, 30);

        jLabel7.setText("Nombre");
        add(jLabel7);
        jLabel7.setBounds(40, 70, 50, 14);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Modificar horario de disponibilidad del profesor");
        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel8);
        jLabel8.setBounds(480, 430, 550, 30);

        jScrollPane1.setViewportView(jListCursos);

        add(jScrollPane1);
        jScrollPane1.setBounds(480, 310, 290, 110);

        jLabel9.setText("Cédula");
        add(jLabel9);
        jLabel9.setBounds(40, 110, 50, 14);

        jButtonAgregarCursoAProfe.setText("Agregar");
        jButtonAgregarCursoAProfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarCursoAProfeActionPerformed(evt);
            }
        });
        add(jButtonAgregarCursoAProfe);
        jButtonAgregarCursoAProfe.setBounds(240, 250, 90, 23);

        jButtonQuitarCurso.setText("Quitar");
        jButtonQuitarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarCursoActionPerformed(evt);
            }
        });
        add(jButtonQuitarCurso);
        jButtonQuitarCurso.setBounds(240, 280, 90, 23);
        add(jTextFieldCodigo);
        jTextFieldCodigo.setBounds(40, 260, 140, 30);

        jLabel3.setText("Codigo Del Curso:");
        add(jLabel3);
        jLabel3.setBounds(40, 240, 140, 14);

        jButtonAgregarHorario.setText("Agregar");
        jButtonAgregarHorario.setEnabled(false);
        jButtonAgregarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarHorarioActionPerformed(evt);
            }
        });
        add(jButtonAgregarHorario);
        jButtonAgregarHorario.setBounds(740, 470, 110, 30);

        jButtonQuitarHorario.setText("Quitar");
        jButtonQuitarHorario.setEnabled(false);
        jButtonQuitarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarHorarioActionPerformed(evt);
            }
        });
        add(jButtonQuitarHorario);
        jButtonQuitarHorario.setBounds(870, 470, 110, 30);

        jComboBoxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Horario", "07:00 - 09:10", "09:15 - 11:30", "12:30 - 14:45", "14:50 - 16:05" }));
        jComboBoxHorario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHorarioItemStateChanged(evt);
            }
        });
        add(jComboBoxHorario);
        jComboBoxHorario.setBounds(570, 470, 130, 30);

        jComboBoxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "L", "K", "M", "J", "V" }));
        jComboBoxDia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDiaItemStateChanged(evt);
            }
        });
        add(jComboBoxDia);
        jComboBoxDia.setBounds(480, 470, 80, 30);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Capacitado para dar los cursos:");
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel10);
        jLabel10.setBounds(480, 270, 550, 30);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Horario de disponibilidad del profesor");
        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel11);
        jLabel11.setBounds(480, 30, 550, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Registrar profesor");
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel4);
        jLabel4.setBounds(40, 320, 290, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxProfesoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxProfesoresItemStateChanged
        try {
            this.jTextFieldCedula.setEditable(false);
            this.jTextFieldNombre.setEditable(false);
            this.jButtonGuardar.setEnabled(false);
            if( ! jComboBoxProfesores.getSelectedItem().toString().equals("Seleccione el profesor") ) {
                String select = jComboBoxProfesores.getSelectedItem().toString();
                String ced = select.split(",")[0];
                Profesor profe = lista.buscarProfesor(ced);
                if(profe != null) {
                    this.jTextFieldNombre.setText(profe.getNombre());
                    this.jTextFieldCedula.setText(profe.getCedula());
                    this.cedula = profe.getCedula();
                    this.jButtonEditar.setEnabled(true);
                    this.jButtonEliminar.setEnabled(true);
                    mostrarHorario();
                    mostrarCursosQueDa();
                }
            } else {
                limpiarHorario();
                this.jListCursos.setModel(new DefaultListModel<>());
                this.jTextFieldNombre.setText("");
                this.jTextFieldCedula.setText("");
                this.jButtonEditar.setEnabled(false);
                this.jButtonEliminar.setEnabled(false);
                this.cedula = null;
            }
        } catch (NullPointerException e) {}
    }//GEN-LAST:event_jComboBoxProfesoresItemStateChanged

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        this.jTextFieldNombre.setEditable(true);
        this.jTextFieldCedula.setEditable(true);
        this.jButtonGuardar.setEnabled(true);
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // Extrae la información
        String nuevoNombre = jTextFieldNombre.getText();
        String nuevaCedula = jTextFieldCedula.getText();
        
        boolean insertado = lista.editarProfesor(nuevoNombre, cedula, nuevaCedula);
        if(insertado) {
            this.jTextFieldNombre.setEditable(false);
            this.jTextFieldCedula.setEditable(false);
            this.jButtonGuardar.setEnabled(false);
            cargarComboBox();
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        boolean estaEliminado = lista.borrarProfesor(cedula);
        if(estaEliminado) {
            cargarComboBox();
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        // Extrae la información
        String nuevoNombre = jTextFieldNombre1.getText();
        String nuevaCedula = jTextFieldCedula1.getText();
        boolean seRegistro = lista.insertarProfesor(nuevaCedula, nuevoNombre);
        if(seRegistro) {
            cargarComboBox();
            jTextFieldCedula1.setText("");
            jTextFieldNombre1.setText("");
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonAgregarCursoAProfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarCursoAProfeActionPerformed
        String idCurso = jTextFieldCodigo.getText();
        boolean agregado = lista.relacionarCursoAProfe(cedula, idCurso);
        if(agregado) {
            jTextFieldCodigo.setText("");
            // actualiza la lista de cursos visualmente
            jListCursos.setModel(lista.cursosQueImparte(cedula));
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonAgregarCursoAProfeActionPerformed

    private void jButtonQuitarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarCursoActionPerformed
        String idCurso = jTextFieldCodigo.getText();
        boolean quitado = lista.quitarCursoAProfe(cedula, idCurso);
        if(quitado) {
            jTextFieldCodigo.setText("");
            // actualiza la lista de cursos visualmente
            jListCursos.setModel(lista.cursosQueImparte(cedula));
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonQuitarCursoActionPerformed

    private void validar() { // Validar que seleccionó un dia y una hora
        try {
            if( ! jComboBoxHorario.getSelectedItem().toString().equals("Horario") 
                    && ! jComboBoxDia.getSelectedItem().toString().equals("Dia")) {
                jButtonAgregarHorario.setEnabled(true);
                jButtonQuitarHorario.setEnabled(true);
            } else {
                jButtonAgregarHorario.setEnabled(false);
                jButtonQuitarHorario.setEnabled(false);
            }
        } catch (NullPointerException e) {}
    }
    
    private void jComboBoxDiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDiaItemStateChanged
        validar();
    }//GEN-LAST:event_jComboBoxDiaItemStateChanged
    
    private void jComboBoxHorarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHorarioItemStateChanged
        validar();
    }//GEN-LAST:event_jComboBoxHorarioItemStateChanged

    private void jButtonAgregarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarHorarioActionPerformed
        String dia = jComboBoxDia.getSelectedItem().toString();
        String hor = jComboBoxHorario.getSelectedItem().toString();
        boolean agregado = lista.agregarDisponibilidadAProfe(cedula, dia, hor);
        if(agregado) {
            mostrarHorario();
            jComboBoxDia.setSelectedIndex(0);
            jComboBoxHorario.setSelectedIndex(0);
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonAgregarHorarioActionPerformed

    private void jButtonQuitarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarHorarioActionPerformed
        String dia = jComboBoxDia.getSelectedItem().toString();
        String hor = jComboBoxHorario.getSelectedItem().toString();
        boolean quitado = lista.quitarDisponibilidadAProfe(cedula, dia, hor);
        if(quitado) {
            mostrarHorario();
            jComboBoxDia.setSelectedIndex(0);
            jComboBoxHorario.setSelectedIndex(0);
            // Para actualizar la información en las otras ventanas
            notificarModificaciones();
        }
    }//GEN-LAST:event_jButtonQuitarHorarioActionPerformed

    private void limpiarHorario() {
        jTextAreaL7.setBackground(Color.WHITE); jTextAreaL12.setBackground(Color.WHITE);
        jTextAreaL9.setBackground(Color.WHITE); jTextAreaL14.setBackground(Color.WHITE);
        jTextAreaL9.setBackground(Color.WHITE); jTextAreaL14.setBackground(Color.WHITE);
        jTextAreaK7.setBackground(Color.WHITE); jTextAreaK12.setBackground(Color.WHITE);
        jTextAreaK9.setBackground(Color.WHITE); jTextAreaK14.setBackground(Color.WHITE);
        jTextAreaM7.setBackground(Color.WHITE); jTextAreaM12.setBackground(Color.WHITE);
        jTextAreaM9.setBackground(Color.WHITE); jTextAreaM14.setBackground(Color.WHITE);
        jTextAreaJ7.setBackground(Color.WHITE); jTextAreaJ12.setBackground(Color.WHITE);
        jTextAreaJ9.setBackground(Color.WHITE); jTextAreaJ14.setBackground(Color.WHITE);
        jTextAreaV7.setBackground(Color.WHITE); jTextAreaV12.setBackground(Color.WHITE);
        jTextAreaV9.setBackground(Color.WHITE); jTextAreaV14.setBackground(Color.WHITE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarCursoAProfe;
    private javax.swing.JButton jButtonAgregarHorario;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonQuitarCurso;
    private javax.swing.JButton jButtonQuitarHorario;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JComboBox<String> jComboBoxDia;
    private javax.swing.JComboBox<String> jComboBoxHorario;
    private javax.swing.JComboBox<String> jComboBoxProfesores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListCursos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextAreaJ12;
    private javax.swing.JTextArea jTextAreaJ14;
    private javax.swing.JTextArea jTextAreaJ7;
    private javax.swing.JTextArea jTextAreaJ9;
    private javax.swing.JTextArea jTextAreaK12;
    private javax.swing.JTextArea jTextAreaK14;
    private javax.swing.JTextArea jTextAreaK7;
    private javax.swing.JTextArea jTextAreaK9;
    private javax.swing.JTextArea jTextAreaL12;
    private javax.swing.JTextArea jTextAreaL14;
    private javax.swing.JTextArea jTextAreaL7;
    private javax.swing.JTextArea jTextAreaL9;
    private javax.swing.JTextArea jTextAreaM12;
    private javax.swing.JTextArea jTextAreaM14;
    private javax.swing.JTextArea jTextAreaM7;
    private javax.swing.JTextArea jTextAreaM9;
    private javax.swing.JTextArea jTextAreaV12;
    private javax.swing.JTextArea jTextAreaV14;
    private javax.swing.JTextArea jTextAreaV7;
    private javax.swing.JTextArea jTextAreaV9;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldCedula1;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre1;
    // End of variables declaration//GEN-END:variables
}

package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.lang.NullPointerException;
import javax.swing.JOptionPane;
import main.Listas;
import models.*;

/**
 *
 * @author Usuario
 */
public class Consulta4 extends javax.swing.JPanel {

    private Principal pr;
    private Listas listas;
    private int indice;
    private int totCom;
    private ArrayList<ArrayList<String[]>> horarios;
    private int total; /* EL LEN DE LOS HORARIOS */
    
    public Consulta4(Principal p) {
        this.pr = p;
        this.listas = Listas.getInstance();
        this.indice = 0;
        this.horarios = null;
        initComponents();
    }
    
    private void desplegar(String dia, String horaIni, String horaFin, String text, int c) {
        Color color = pr.colores.get(c);
        switch (dia) {
                case "L":
                    if (horaIni.equals("7")) {
                        jTextAreaL7.setText(text);
                        jTextAreaL7.setBackground(color);
                        if (horaFin.equals("11")) {jTextAreaL9.setBackground(color);}
                    } else if (horaIni.equals("12")) {
                        jTextAreaL12.setText(text);
                        jTextAreaL12.setBackground(color);
                        if (horaFin.equals("16")) {jTextAreaL14.setBackground(color);}
                    }
                    break;
                case "K":
                    if (horaIni.equals("7")) {
                        jTextAreaK7.setText(text);
                        jTextAreaK7.setBackground(color);
                        if (horaFin.equals("11")) {jTextAreaK9.setBackground(color);}
                    } else if (horaIni.equals("12")) {
                        jTextAreaK12.setText(text);
                        jTextAreaK12.setBackground(color);
                        if (horaFin.equals("16")) {jTextAreaK14.setBackground(color);}
                    }
                    break;
                case "M":
                    if (horaIni.equals("7")) {
                        jTextAreaM7.setText(text);
                        jTextAreaM7.setBackground(color);
                        if (horaFin.equals("11")) {jTextAreaM9.setBackground(color);}
                    } else if (horaIni.equals("12")) {
                        jTextAreaM12.setText(text);
                        jTextAreaM12.setBackground(color);
                        if (horaFin.equals("16")) {jTextAreaM14.setBackground(color);}
                    }
                    break;
                case "J":
                    if (horaIni.equals("7")) {
                        jTextAreaJ7.setText(text);
                        jTextAreaJ7.setBackground(color);
                        if (horaFin.equals("11")) {jTextAreaJ9.setBackground(color);}
                    } else if (horaIni.equals("12")) {
                        jTextAreaJ12.setText(text);
                        jTextAreaJ12.setBackground(color);
                        if (horaFin.equals("16")) {jTextAreaJ14.setBackground(color);}
                    }
                    break;
                default:
                    if (horaIni.equals("7")) {
                        jTextAreaV7.setText(text);
                        jTextAreaV7.setBackground(color);
                        if (horaFin.equals("11")) {jTextAreaV9.setBackground(color);}
                    } else if (horaIni.equals("12")) {
                        jTextAreaV12.setText(text);
                        jTextAreaV12.setBackground(color);
                        if (horaFin.equals("16")) {jTextAreaV14.setBackground(color);}
                    }
                    break;
            }
    }
    
    private void mostrarHorario4() {
        jLabelNumero.setText((indice + 1 ) + "");
        limpiarTextAreas();
        ArrayList<String[]> solucion = horarios.get(indice);
        
        String dia, idCurso, horaIni, horaFin, color;
        Curso c;
        
        for (String[] array : solucion) {
            dia     = array[0].replace("'", ""); // DIA
            idCurso = array[1].replace("'", ""); // ID CURSO
            horaIni = array[2]; // HORA INICIO
            horaFin = array[3]; // HORA FINAL
            color   = array[4];
            
            c = listas.buscarCurso(idCurso);
            
            String text = c.getIdCurso() + "\n" + c.getNombre() + "\nTipo: " 
                    + c.getTipo() + "\nCreditos: " + c.getCreditos();
            desplegar(dia, horaIni, horaFin, text, Integer.parseInt(color));
        }
    }

    private void limpiarTextAreas() {
        jTextAreaL7.setText(""); jTextAreaL12.setText(""); 
        jTextAreaL9.setText(""); jTextAreaL14.setText("");
        jTextAreaL9.setText(""); jTextAreaL14.setText("");
        jTextAreaK7.setText(""); jTextAreaK12.setText("");
        jTextAreaK9.setText(""); jTextAreaK14.setText("");
        jTextAreaM7.setText(""); jTextAreaM12.setText("");
        jTextAreaM9.setText(""); jTextAreaM14.setText("");
        jTextAreaJ7.setText(""); jTextAreaJ12.setText("");
        jTextAreaJ9.setText(""); jTextAreaJ14.setText("");
        jTextAreaV7.setText(""); jTextAreaV12.setText("");
        jTextAreaV9.setText(""); jTextAreaV14.setText("");
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
    
    public void consulta4(String cursos, int totCom) {
        
        this.totCom = totCom;
        jButtonAnterior.setEnabled(false);
        jButtonSiguiente.setEnabled(false);
        
        if(totCom > 1) {
            jButtonSiguiente.setEnabled(true);
            jButtonMostrarIndice.setEnabled(true);
            jTextFieldIndice.setEditable(true);
        } else {
            jButtonMostrarIndice.setEnabled(false);
            jTextFieldIndice.setEditable(false);
        }
        
        // PARA VALIDAR SI YA ALGUNA OTRA CONSULTA SUBIÓ LOS HECHOS A PROLOG.
        // TAMBÉN VALIDA SI ALGUNO DE LOS DATOS EN LAS LISTAS FUERON MODIFICADOS,
        // DE SER ASÍ BORRA LOS DATOS DE PROLOG Y LOS VUELVE A INSERTAR.
        pr.prolog.validar();
        
        horarios = pr.prolog.consulta4(cursos, totCom);
        
        indice = 0;
        jLabelNumero.setText((indice + 1) + "");
        if(horarios != null) {
            mostrarHorario4();
            total = horarios.size();
            this.jLabelTotal.setText(total + "");
        }
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
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
        jScrollPane25 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButtonAnterior = new javax.swing.JButton();
        jButtonSiguiente = new javax.swing.JButton();
        jButtonConsulta4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabelNumero = new javax.swing.JLabel();
        jButtonMostrarIndice = new javax.swing.JButton();
        jTextFieldIndice = new javax.swing.JTextField();
        jLabelTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("      Hora        |                         Lunes                      |                        Martes                      |                     Miércoles                    |                         Jueves                     |                     Viernes");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        add(jLabel2);
        jLabel2.setBounds(110, 120, 920, 17);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(6);
        jTextArea1.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea1.setRows(2);
        jTextArea1.setTabSize(1);
        jTextArea1.setText("\n\t\t07:00\n\t\t09:10");
        jTextArea1.setBorder(null);
        jTextArea1.setEnabled(false);
        jTextArea1.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane24.setViewportView(jTextArea1);

        add(jScrollPane24);
        jScrollPane24.setBounds(110, 140, 70, 70);

        jTextAreaL7.setColumns(10);
        jTextAreaL7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL7.setRows(4);
        jTextAreaL7.setTabSize(2);
        jTextAreaL7.setText("\n");
        jTextAreaL7.setBorder(null);
        jTextAreaL7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane23.setViewportView(jTextAreaL7);

        add(jScrollPane23);
        jScrollPane23.setBounds(180, 140, 170, 70);

        jTextAreaK7.setColumns(10);
        jTextAreaK7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK7.setRows(4);
        jTextAreaK7.setTabSize(2);
        jTextAreaK7.setBorder(null);
        jTextAreaK7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane5.setViewportView(jTextAreaK7);

        add(jScrollPane5);
        jScrollPane5.setBounds(350, 140, 170, 70);

        jTextAreaM7.setColumns(10);
        jTextAreaM7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM7.setRows(4);
        jTextAreaM7.setTabSize(2);
        jTextAreaM7.setBorder(null);
        jTextAreaM7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane6.setViewportView(jTextAreaM7);

        add(jScrollPane6);
        jScrollPane6.setBounds(520, 140, 170, 70);

        jTextAreaJ7.setColumns(10);
        jTextAreaJ7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ7.setRows(4);
        jTextAreaJ7.setTabSize(2);
        jTextAreaJ7.setBorder(null);
        jTextAreaJ7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane7.setViewportView(jTextAreaJ7);

        add(jScrollPane7);
        jScrollPane7.setBounds(690, 140, 170, 70);

        jTextAreaV7.setColumns(10);
        jTextAreaV7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV7.setRows(4);
        jTextAreaV7.setTabSize(2);
        jTextAreaV7.setBorder(null);
        jTextAreaV7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane4.setViewportView(jTextAreaV7);

        add(jScrollPane4);
        jScrollPane4.setBounds(860, 140, 170, 70);

        jTextAreaV9.setColumns(10);
        jTextAreaV9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV9.setRows(4);
        jTextAreaV9.setTabSize(2);
        jTextAreaV9.setBorder(null);
        jTextAreaV9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane12.setViewportView(jTextAreaV9);

        add(jScrollPane12);
        jScrollPane12.setBounds(860, 210, 170, 70);

        jTextAreaJ9.setColumns(10);
        jTextAreaJ9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ9.setRows(4);
        jTextAreaJ9.setTabSize(2);
        jTextAreaJ9.setBorder(null);
        jTextAreaJ9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane11.setViewportView(jTextAreaJ9);

        add(jScrollPane11);
        jScrollPane11.setBounds(690, 210, 170, 70);

        jTextAreaM9.setColumns(10);
        jTextAreaM9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM9.setRows(4);
        jTextAreaM9.setTabSize(2);
        jTextAreaM9.setBorder(null);
        jTextAreaM9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane10.setViewportView(jTextAreaM9);

        add(jScrollPane10);
        jScrollPane10.setBounds(520, 210, 170, 70);

        jTextAreaK9.setColumns(10);
        jTextAreaK9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK9.setRows(4);
        jTextAreaK9.setTabSize(2);
        jTextAreaK9.setBorder(null);
        jTextAreaK9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane9.setViewportView(jTextAreaK9);

        add(jScrollPane9);
        jScrollPane9.setBounds(350, 210, 170, 70);

        jTextAreaL9.setColumns(10);
        jTextAreaL9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL9.setRows(4);
        jTextAreaL9.setTabSize(2);
        jTextAreaL9.setText("\n");
        jTextAreaL9.setBorder(null);
        jTextAreaL9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane8.setViewportView(jTextAreaL9);

        add(jScrollPane8);
        jScrollPane8.setBounds(180, 210, 170, 70);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(6);
        jTextArea2.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea2.setRows(2);
        jTextArea2.setTabSize(1);
        jTextArea2.setText("\n\t\t09:15\n\t\t11:30");
        jTextArea2.setBorder(null);
        jTextArea2.setEnabled(false);
        jTextArea2.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane3.setViewportView(jTextArea2);

        add(jScrollPane3);
        jScrollPane3.setBounds(110, 210, 70, 70);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(6);
        jTextArea3.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea3.setRows(2);
        jTextArea3.setTabSize(1);
        jTextArea3.setText("\n\t\t12:30\n\t\t14:45");
        jTextArea3.setBorder(null);
        jTextArea3.setEnabled(false);
        jTextArea3.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane26.setViewportView(jTextArea3);

        add(jScrollPane26);
        jScrollPane26.setBounds(110, 290, 70, 70);

        jTextAreaL12.setColumns(10);
        jTextAreaL12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL12.setRows(4);
        jTextAreaL12.setTabSize(2);
        jTextAreaL12.setText("\n");
        jTextAreaL12.setBorder(null);
        jTextAreaL12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane13.setViewportView(jTextAreaL12);

        add(jScrollPane13);
        jScrollPane13.setBounds(180, 290, 170, 70);

        jTextAreaK12.setColumns(10);
        jTextAreaK12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK12.setRows(4);
        jTextAreaK12.setTabSize(2);
        jTextAreaK12.setBorder(null);
        jTextAreaK12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane14.setViewportView(jTextAreaK12);

        add(jScrollPane14);
        jScrollPane14.setBounds(350, 290, 170, 70);

        jTextAreaM12.setColumns(10);
        jTextAreaM12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM12.setRows(4);
        jTextAreaM12.setTabSize(2);
        jTextAreaM12.setBorder(null);
        jTextAreaM12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane15.setViewportView(jTextAreaM12);

        add(jScrollPane15);
        jScrollPane15.setBounds(520, 290, 170, 70);

        jTextAreaJ12.setColumns(10);
        jTextAreaJ12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ12.setRows(4);
        jTextAreaJ12.setTabSize(2);
        jTextAreaJ12.setBorder(null);
        jTextAreaJ12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane16.setViewportView(jTextAreaJ12);

        add(jScrollPane16);
        jScrollPane16.setBounds(690, 290, 170, 70);

        jTextAreaV12.setColumns(10);
        jTextAreaV12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV12.setRows(4);
        jTextAreaV12.setTabSize(2);
        jTextAreaV12.setBorder(null);
        jTextAreaV12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane17.setViewportView(jTextAreaV12);

        add(jScrollPane17);
        jScrollPane17.setBounds(860, 290, 170, 70);

        jTextAreaV14.setColumns(10);
        jTextAreaV14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV14.setRows(4);
        jTextAreaV14.setTabSize(2);
        jTextAreaV14.setBorder(null);
        jTextAreaV14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane22.setViewportView(jTextAreaV14);

        add(jScrollPane22);
        jScrollPane22.setBounds(860, 360, 170, 70);

        jTextAreaJ14.setColumns(10);
        jTextAreaJ14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ14.setRows(4);
        jTextAreaJ14.setTabSize(2);
        jTextAreaJ14.setBorder(null);
        jTextAreaJ14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane21.setViewportView(jTextAreaJ14);

        add(jScrollPane21);
        jScrollPane21.setBounds(690, 360, 170, 70);

        jTextAreaM14.setColumns(10);
        jTextAreaM14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM14.setRows(4);
        jTextAreaM14.setTabSize(2);
        jTextAreaM14.setBorder(null);
        jTextAreaM14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane20.setViewportView(jTextAreaM14);

        add(jScrollPane20);
        jScrollPane20.setBounds(520, 360, 170, 70);

        jTextAreaK14.setColumns(10);
        jTextAreaK14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK14.setRows(4);
        jTextAreaK14.setTabSize(2);
        jTextAreaK14.setBorder(null);
        jTextAreaK14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane19.setViewportView(jTextAreaK14);

        add(jScrollPane19);
        jScrollPane19.setBounds(350, 360, 170, 70);

        jTextAreaL14.setColumns(10);
        jTextAreaL14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL14.setRows(4);
        jTextAreaL14.setTabSize(2);
        jTextAreaL14.setText("\n");
        jTextAreaL14.setBorder(null);
        jTextAreaL14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane18.setViewportView(jTextAreaL14);

        add(jScrollPane18);
        jScrollPane18.setBounds(180, 360, 170, 70);

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(6);
        jTextArea4.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea4.setRows(2);
        jTextArea4.setTabSize(1);
        jTextArea4.setText("\n\t\t14:50\n\t\t16:05");
        jTextArea4.setBorder(null);
        jTextArea4.setEnabled(false);
        jTextArea4.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane25.setViewportView(jTextArea4);

        add(jScrollPane25);
        jScrollPane25.setBounds(110, 360, 70, 70);

        jButtonAnterior.setText("Anterior");
        jButtonAnterior.setEnabled(false);
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });
        add(jButtonAnterior);
        jButtonAnterior.setBounds(360, 440, 170, 50);

        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.setEnabled(false);
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });
        add(jButtonSiguiente);
        jButtonSiguiente.setBounds(560, 440, 170, 50);

        jButtonConsulta4.setText("Ir a seleccionar cursos");
        jButtonConsulta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsulta4ActionPerformed(evt);
            }
        });
        add(jButtonConsulta4);
        jButtonConsulta4.setBounds(110, 50, 250, 50);

        jLabel12.setText("Horario ");
        add(jLabel12);
        jLabel12.setBounds(850, 100, 60, 14);

        jLabelNumero.setText("#");
        add(jLabelNumero);
        jLabelNumero.setBounds(930, 100, 50, 14);

        jButtonMostrarIndice.setText("Ir al horario #");
        jButtonMostrarIndice.setEnabled(false);
        jButtonMostrarIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarIndiceActionPerformed(evt);
            }
        });
        add(jButtonMostrarIndice);
        jButtonMostrarIndice.setBounds(770, 50, 170, 30);

        jTextFieldIndice.setEditable(false);
        add(jTextFieldIndice);
        jTextFieldIndice.setBounds(950, 50, 80, 30);

        jLabelTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabelTotal);
        jLabelTotal.setBounds(950, 10, 80, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("    Horarios generados");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        add(jLabel1);
        jLabel1.setBounds(770, 10, 170, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        indice--;
        
        // MUESTRA OBJECTO EN LA POSICIÓN INDICADA
        mostrarHorario4();

        if(indice == 0) {
            jButtonAnterior.setEnabled(false);
        }

        if( ! jButtonSiguiente.isEnabled() ) {
            jButtonSiguiente.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        indice++;
        
        // MUESTRA OBJECTO EN LA POSICIÓN INDICADA
        mostrarHorario4();

        if(indice == (totCom - 1) || indice == (horarios.size() - 1)) {
            jButtonSiguiente.setEnabled(false);
        }

        if( ! jButtonAnterior.isEnabled() ) {
            jButtonAnterior.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

    private void jButtonConsulta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsulta4ActionPerformed
        SeleccionarCursos n = new SeleccionarCursos(this);
        n.setSize(new Dimension(1160, 580));
        n.setMinimumSize(new Dimension(1160, 580));
        n.setLocationRelativeTo(null);
        n.setVisible(true);
    }//GEN-LAST:event_jButtonConsulta4ActionPerformed

    private void jButtonMostrarIndiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarIndiceActionPerformed
        try {
            int i = Integer.parseInt(jTextFieldIndice.getText());
            
            if(i > totCom || i <= 0 || i > total) {
                JOptionPane.showMessageDialog(this, "Se pasa del límite de las combinaciones generadas,"
                        + " máximo " + total + " horarios", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            indice = i - 1;
            if(indice == 0) {
                jButtonAnterior.setEnabled(false);
                jButtonSiguiente.setEnabled(true);
            } else if (indice == (total - 1)) {
                jButtonSiguiente.setEnabled(false);
                jButtonAnterior.setEnabled(true);
            } else {
                jButtonAnterior.setEnabled(true);
                jButtonSiguiente.setEnabled(true);
            }
        
            // MUESTRA OBJECTO EN LA POSICIÓN INDICADA
            jLabelNumero.setText((indice + 1 ) + "");
            mostrarHorario4();
            
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("algo paso");
        }
    }//GEN-LAST:event_jButtonMostrarIndiceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonConsulta4;
    private javax.swing.JButton jButtonMostrarIndice;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelTotal;
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
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
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
    private javax.swing.JTextField jTextFieldIndice;
    // End of variables declaration//GEN-END:variables
}

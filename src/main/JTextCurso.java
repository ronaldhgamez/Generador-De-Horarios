package main;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JTextArea;

/**
 * CLASE UTILIZADA EN LA VENTANA SELECCIONAR CURSOS.
 * SE UTILIZA PARA PERMITIRLE AL USUARIO SELECCIONAR LOS CURSOS EN LA CONSULTA 4
 * DE MANERA VISUAL Y HACER QUE CAMBIEN DE COLOR LOS TEXTAREAS.
 * @author Ronaldhg
 */
public class JTextCurso extends JTextArea implements MouseListener {
   
    /* ALMACENA LA INFORMACIÓN DEL CURSO */
    private String idCurso;
    private int semestre;
    
    /* PARA VALIDAR QUÉ CURSOS FUERON SELECCINADOS */
    private boolean isSelected;

    public JTextCurso(String idCurso, String text, int semestre, int rows, int columns) {
        super(text, rows, columns);
        this.idCurso = idCurso;
        this.isSelected = false;
        this.semestre = semestre;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(! isSelected) {
            this.setBackground(new Color(102,255,0));
            isSelected = true;
        } else {
            this.setBackground(Color.white);
            isSelected = false;
        }
    }
    
    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    public String getIdCurso() {
        return idCurso;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}

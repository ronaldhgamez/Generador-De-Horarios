package models;

/**
 *
 * @author Ronaldhg
 */
public class Curso {
    
    private String idCurso;
    private String nombre;
    private int creditos;
    private int semestre;
    private String tipo;
    private int clasesXSem;

    public Curso(String idCurso, String nombre, int creditos, int semestre, String tipo, int clasesXSem) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
        this.tipo = tipo;
        this.clasesXSem = clasesXSem;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getClasesXSem() {
        return clasesXSem;
    }

    public void setClasesXSem(int clasesXSem) {
        this.clasesXSem = clasesXSem;
    }

    @Override
    public String toString() {
        return "Curso{" + "\n\tidCurso = " + idCurso + "\n\tnombre = " 
                + nombre + "\n\tcreditos = " + creditos + "\n\tsemestre = "
                + semestre + "\n\ttipo = " + tipo + "\n\tclasesXSem = " 
                + clasesXSem + "\n}";
    }
    
}

package models;

/**
 *
 * @author Ronaldhg
 */
public class Imparte {
    
    private String idCurso;
    private String cedula;

    public Imparte(String idCurso, String cedula) {
        this.idCurso = idCurso;
        this.cedula = cedula;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Imparte{" + "idCurso=" + idCurso + ", cedula=" + cedula + '}';
    }
    
}

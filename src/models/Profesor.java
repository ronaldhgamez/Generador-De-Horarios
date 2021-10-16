package models;

/**
 * @author Ronaldhg
 */
public class Profesor {
    
    private String cedula;
    private String nombre;

    public Profesor(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Profesor{" + "\n\tcedula = " + cedula 
                + "\n\tnombre = " + nombre + "\n}";
    }

}

package models;

/**
 *
 * @author Ronaldhg
 */
public class Disponible {
    
    private String cedula;
    private String dia;
    private int horaIni;
    private int horaFin;

    public Disponible(String cedula, String dia, int horaIni, int horaFin) {
        this.cedula = cedula;
        this.dia = dia;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(int horaIni) {
        this.horaIni = horaIni;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "Disponible{" + "cedula=" + cedula + ", dia=" + dia 
                + ", horaIni=" + horaIni + ", horaFin=" + horaFin + '}';
    }
    
}

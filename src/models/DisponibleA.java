package models;

/**
 * CLASE PARA ALMACENAR UN HORARIO DE DISPONIBILIDAD PARA UN AULA.
 * EL AULA PUEDE ESTAR ASOCIADO A NIGUNGO O VARIOS HORARIOS.
 * @author Ronaldhg
 */
public class DisponibleA {
    
    private String idAula;
    private String dia;
    private int horaIni;
    private int horaFin;

    public DisponibleA(String idAula, String dia, int horaIni, int horaFin) {
        this.idAula = idAula;
        this.dia = dia;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
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
        return "DisponibleA{" + "idAula=" + idAula + ", dia=" + dia 
                + ", horaIni=" + horaIni + ", horaFin=" + horaFin + '}';
    }
    
}

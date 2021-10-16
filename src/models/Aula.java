package models;

/**
 *
 * @author Ronaldhg
 */
public class Aula {
    
    private String idAula;
    private String tipo;
    private int capacidad;

    public Aula(String idAula, String tipo, int capacidad) {
        this.idAula = idAula;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Aula{" + "\n\tidAula = " + idAula + "\n\ttipo = " + tipo 
                + "\n\tcapacidad = " + capacidad + "\n}";
    }
    
}

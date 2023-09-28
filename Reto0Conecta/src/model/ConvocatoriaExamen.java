package model;

import java.io.Serializable;
import java.time.LocalDate;
import utiles.Util;

/**
 * This is the convocatoria class.
 * @author alexs
 */
public class ConvocatoriaExamen implements Serializable{
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
    private Integer idEnunciado;

    public ConvocatoriaExamen(String convocatoria, String descripcion, LocalDate fecha, String curso, Integer idEnunciado) {
        this.convocatoria = convocatoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.curso = curso;
        this.idEnunciado = idEnunciado;
    }

    public ConvocatoriaExamen() {
        
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }
    public void setDatos(){
        System.out.println("Introduce el nombre de la convocatoria:");
        convocatoria = Util.introducirCadena();
        System.out.println("Escribe una breve descripción de la convocatoria.");
        descripcion = Util.introducirCadena();
        System.out.println("¿En qué fecha está programada ésta convocatoria? (dia/mes/año)");
        fecha = Util.leerFechaDMA();
        System.out.println("¿Para qué curso es ésta convocatoria?");
        curso = Util.introducirCadena();
        idEnunciado = null;
    }
    
    public void getDatos(){
        System.out.println("Convocatoria: " + getConvocatoria()
                            + "\nDescripción: " + getDescripcion()
                            + "\nFecha: " + getFecha()
                            + "\nCurso: " + getCurso()
                            + "\nEnunciado: " + getIdEnunciado());
    }
}

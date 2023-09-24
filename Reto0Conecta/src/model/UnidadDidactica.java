package model;

import java.util.ArrayList;
import java.util.List;
import controller.Util;

/**
 * Represents an educational unit.
 *
 * This class encapsulates information about an educational unit, including its
 * ID, acronym, title, evaluation, and description. It provides methods to set
 * and retrieve these attributes.
 *
 * This class also includes a method, setDatos(), that prompts the user to enter
 * information for the educational unit, with validation to ensure that the data
 * entered is in the correct format.
 *
 * @author Irati Garzón
 */
public class UnidadDidactica {

    private Integer id;
    private String acronimo;
    private String titulo;
    private String evaluacion;
    private String descripcion;

    private List<Enunciado> enunciados = new ArrayList<>();

    /**
     * Get the ID of the educational unit.
     *
     * @return The ID of the educational unit.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the ID of the educational unit.
     *
     * @param id The ID to set for the educational unit.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the acronym of the educational unit.
     *
     * @return The acronym of the educational unit.
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * Set the acronym of the educational unit.
     *
     * @param acronimo The acronym to set for the educational unit.
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Get the title of the educational unit.
     *
     * @return The title of the educational unit.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Set the title of the educational unit.
     *
     * @param titulo The title to set for the educational unit.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Get the evaluation of the educational unit.
     *
     * @return The evaluation of the educational unit.
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * Set the evaluation of the educational unit.
     *
     * @param evaluacion The evaluation to set for the educational unit.
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Get the description of the educational unit.
     *
     * @return The description of the educational unit.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the description of the educational unit.
     *
     * @param descripcion The description to set for the educational unit.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     /**
     * Prompt the user to enter information for the educational unit.
     * 
     * This method uses user prompts and validation to ensure that the entered
     * information is in the correct format. It repeatedly prompts the user until
     * valid data is provided for each attribute.
     */
    public void setDatos() {
        boolean correct = false;
        while (!correct) {
            System.out.println("Insert the acronym. REMEMBER: The acronym can only consist of a maximum of 4 letters.");
            acronimo = Util.introducirCadena();
            if (!acronimo.isEmpty() && acronimo.length() <= 4 && acronimo.matches("[a-zA-Z]+")) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }
        correct = false;

        while (!correct) {
            System.out.println("Insert the title: ");
            titulo = Util.introducirCadena();
            if (!titulo.isEmpty() && titulo.matches("[a-zA-Z]+")) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }
        correct = false;

        while (!correct) {
            System.out.println("Insert the evaluation: ");
            evaluacion = Util.introducirCadena();
            if (!evaluacion.isEmpty()) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }
        correct = false;

        while (!correct) {
            System.out.println("Insert the description: ");
            descripcion = Util.introducirCadena();
            if (!descripcion.isEmpty()) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }

    }

}

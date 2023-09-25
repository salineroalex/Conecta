package model;

import controller.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an statement. It
 * contains information such as description, difficulty level, availability,
 * file path, and associated educational units.
 *
 * This class also includes a method, setDatos(), that prompts the user to enter
 * information for the exercise with validation to ensure that the data
 * entered is in the correct format.
 * 
 * @author Irati Garzón
 */
public class Enunciado {

    private Integer id;
    private String descripcion;
    private Dificultad nivel;
    private Integer disponible;
    private String ruta;

    private List<String> unidadesDidacticas = new ArrayList<>();

    /**
     * Get the ID of the statement.
     *
     * @return The ID of the statement.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the ID of the statement.
     *
     * @param id The ID of the statement.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the description of the statement.
     *
     * @return The description of the statement.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the description of the statement.
     *
     * @param descripcion The description of the statement.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get the difficulty level of the statement as a string.
     *
     * @return The difficulty level of the statement as a string.
     */
    public String getNivelString() {
        return nivel.toString(); // Convierte el enum a su representación en cadena
    }

    /**
     * Set the difficulty level of the statement based on a string
     * representation.
     *
     * @param nivelString The string representation of the difficulty level.
     */
    public void setNivelString(String nivelString) {
        try {
            nivel = Dificultad.valueOf(nivelString.toLowerCase());
        } catch (IllegalArgumentException e) {
            nivel = null;
        }
    }

    /**
     * Get the file path associated with the statement.
     *
     * @return The file path of the statement.
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Set the file path associated with the statement.
     *
     * @param ruta The file path of the statement.
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Get the availability status of the statement (0 for available, 1 for
     * unavailable).
     *
     * @return The availability status of the statement.
     */
    public Integer getDisponible() {
        return disponible;
    }

    /**
     * Set the availability status of the statement (0 for available, 1 for
     * unavailable).
     *
     * @param disponible The availability status of the statement.
     */
    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
    }

    /**
     * Get the list of associated educational units by their acronyms.
     *
     * @return The list of educational unit acronyms.
     */
    public List<String> getUnidadesDidacticas() {
        return unidadesDidacticas;
    }

    /**
     * Set the list of associated educational units by their acronyms.
     *
     * @param unidadesDidacticas The list of educational unit acronyms.
     */
    public void setUnidadesDidacticas(List<String> unidadesDidacticas) {
        this.unidadesDidacticas = unidadesDidacticas;
    }

    /**
     * Set the data for the statement, including description, difficulty level,
     * availability, file path, and associated educational units.
     */
    public void setDatos() {
        String disponibleString = null;
        String opcion = null;
        String unidadString;
        boolean correct = false;

        while (!correct) {
            System.out.println("Insert a description: ");
            descripcion = Util.introducirCadena();
            if (!descripcion.isEmpty() && descripcion.matches("[a-zA-Z]+")) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }
        correct = false;
        while (!correct) {
            System.out.println("Insert  the level (alta, media, baja): ");
            String nivelString = Util.introducirCadena();
            //Converts the string to an enum Dificultad
            try {
                nivel = Dificultad.valueOf(nivelString.toLowerCase());
                correct = true; 
            } catch (IllegalArgumentException e) {
                nivel = null;
                System.out.println("The level information is not valid. It has been set to null. Try again!");
            }
        }
        correct = false;
        while (!correct) {
            System.out.println("Is the resource available? (YES OR NO) ");
            disponibleString = Util.introducirCadena();
            if (!disponibleString.isEmpty() && (disponibleString.equalsIgnoreCase("yes") || disponibleString.equalsIgnoreCase("no"))) {
                correct = true;
                if (disponibleString.equalsIgnoreCase("yes")) {
                    disponible = 0;
                } else {
                    disponible = 1;
                }
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }

        correct = false;
        while (!correct) {
            System.out.println("Insert the path of the doc : ");
            ruta = Util.introducirCadena();
            if (!ruta.isEmpty() && ruta.matches("[a-zA-Z0-9:/\\\\]+\\.doc")) {
                correct = true;
            } else {
                System.out.println("The information is not in the correct format. Try again!");
            }
        }
        correct = false;
        System.out.println("Do you want to introduce an Educational Unit?: (YES OR NO)");
        opcion = Util.introducirCadena();
        while (!correct) {
            if (opcion.equalsIgnoreCase("YES") || opcion.equalsIgnoreCase("NO")) {
                correct = true;
                do {
                    System.out.println("Insert the acronym of an Educational Unit ");
                    unidadString = Util.introducirCadena();
                    unidadesDidacticas.add(unidadString);
                    boolean correct2 = false;
                    while(!correct2){
                        System.out.println("Do you want to insert more educational units? : (YES OR NO)");
                        opcion = Util.introducirCadena();
                        if(opcion.equalsIgnoreCase("yes") || opcion.equalsIgnoreCase("no") ){
                            correct2 = true;
                        }else{
                             System.out.println("The information is not in the correct format. Try again!");
                        }
                    }
                    correct2 = false;
                } while (opcion.equalsIgnoreCase("Please, write YES or NO"));
            }else{
                System.out.println("Please, write YES or NO");
            }
        }

    }
   
}

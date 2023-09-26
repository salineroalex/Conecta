package view;

import controller.Controller;

import controller.DAO;
import controller.DaoDBImplementation;
import model.UnidadDidactica;
import controller.Util;
import exceptions.PersonalizedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;

import controller.Tool;

import model.ConvocatoriaExamen;
import model.Enunciado;

/**
 * Console view implementation. Shows a menu and lets the user choose an option
 * to execute.
 *
 * @author alexs, iratig
 */
public class Menu {

    public void menu(Controller controller) {
        Integer choice;

        do {
            System.out.println("----------MENÚ----------"
                    + "\n1. Crear una Unidad Didáctica y convocatoria de examen."
                    + "\n2. Crear un enunciado de examen agregando las Unidades Didácticas que va a referir."
                    + "\n3. Consultar los enunciados de examen en los que se trata una Unidad Didáctica concreta."
                    + "\n4. Consultar en qué convocatorias se ha utilizado un enunciado concreto."
                    + "\n5. Visualizar el documento de texto asociado a un enunciado."
                    + "\n6. Salir");
            choice = Tool.leerInt();
            switch (choice) {
                case 1:
                    createUDConvocatoria(controller);
                    break;
                case 2:
                    createEnunciado(controller);
                    break;
                case 3:
                    viewEnunciadoByUD(controller);
                    break;
                case 4:
                    viewConvocatoria(controller);
                    break;
                case 5:
                    viewDocument(controller);
                    break;
                case 6:
                    System.out.println("SALIENDO...");
                    break;
            }
        } while (choice != 6);
    }

    // 1
    /**
     *
     * @param controller
     */
    private void createUDConvocatoria(Controller controller) {
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        ConvocatoriaExamen convocatoria = new ConvocatoriaExamen();

        //Space for new UD
        unidadDidactica.setDatos();
        try {
            if (controller.addUnidadDidactica(unidadDidactica)) {
                System.out.println("Educational unit created succesfully");
            }
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }
        //Space for new convocatoria
        convocatoria.setDatos();
        try {
            if (!controller.newConvocatoria(convocatoria)) {
                System.out.println("Something went wrong while writing to the file, please try again.");
            }
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // 2
    /**
     * Creates a new Enunciado by collecting data from the user and adding it to
     * the database using the Controller. Prints a success message upon
     * successful creation.
     */
    private void createEnunciado(Controller controller) {
        Enunciado enunciado = new Enunciado();
        enunciado.setDatos();
        try {
            if (controller.addEnunciado(enunciado) != null) {
                System.out.println("Statement created successfully");
            }
        } catch (PersonalizedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // 3
    /**
     * Lists Enunciado objects associated with a specified educational unit
     * provided by the user. Prints the details of each Enunciado if available.
     */
    private void viewEnunciadoByUD(Controller controller) {
        String unidadDidactica;
        System.out.println("Insert the educational unit: ");
        unidadDidactica = Util.introducirCadena();
        List<Enunciado> enunciados = null;
        try {
            enunciados = controller.listarEnunciados(unidadDidactica);
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }
        if (enunciados != null) {
            for (Enunciado enunciado : enunciados) {
                System.out.println("ID: " + enunciado.getId());
                System.out.println("Descripción: " + enunciado.getDescripcion());
                System.out.println("Nivel: " + enunciado.getNivelString());
                if (enunciado.getDisponible() == 0) {
                    System.out.println("Disponible: " + "YES ");
                } else {
                    System.out.println("Disponible: " + "NO");
                }
                System.out.println("Ruta: " + enunciado.getRuta());
            }
        }

    }

    // 4
    /**
     * Asks user for an enunciado ID, then prints the information about the
     * convocatoria associated to the enunciado ID.
     *
     * @param controller Received parameter to connect with class Controller.
     */
    private void viewConvocatoria(Controller controller) {
        List<Enunciado> enunciados = new ArrayList<>();
        try {
            enunciados = controller.listarEnunciados(null);
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }
        ConvocatoriaExamen convocatoria = new ConvocatoriaExamen();
        Integer idEnunciado;
        Boolean flag = false;

        for (Enunciado enunciado : enunciados) {
            System.out.println(enunciado.getId());
        }
        System.out.println("Enter the ID of the enunciado used in the convocatoria oyu want to find:");
        idEnunciado = Tool.leerInt();
        for (int i = 0; i < enunciados.size(); i++) {
            if (enunciados.get(i).getId().equals(idEnunciado)) {
                i = enunciados.size();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Enunciado not found, please try again with another.");
        } else {
            try {
                convocatoria = controller.searchConvocatoria(idEnunciado);
            } catch (PersonalizedException ex) {
                System.err.println(ex.getMessage());
            }
            if (convocatoria == null) {
                System.out.println("Convocatoria not found, please try again.");
            } else {
                convocatoria.getDatos();
            }
        }
    }

    // 5
    /**
     * Lists all enunciados, asks user for enunciado ID, searches for the path
     * to the document in the enunciado object and opens the document.
     *
     * @param controller Received parameter to connect with class Controller.
     */
    public void viewDocument(Controller controller) {
        Integer idEnunciado;
        Boolean flag = false;
        String path = null;
        List<Enunciado> enunciados = new ArrayList<>();
        try {
            enunciados = controller.listarEnunciados(null);
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }

        for (Enunciado enunciado : enunciados) {
            System.out.println(enunciado.getId() + "\n");
        }
        System.out.println("Enter the ID of the enunciado associated with the file you want to find:");
        idEnunciado = Tool.leerInt();
        for (int i = 0; i < enunciados.size(); i++) {
            if (enunciados.get(i).getId().equals(idEnunciado)) {
                path = enunciados.get(i).getRuta();
                i = enunciados.size();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Enunciado not found, please try again with another.");
        }
        try {
            if (path != null && !controller.openDocument(path)) {
                System.out.println("\nFile not found, make sure the enunciado assigned to the file you are searching is correct.\n");
            }
        } catch (PersonalizedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

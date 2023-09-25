package view;

import controller.Controller;
import controller.Tool;
import java.awt.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.UnidadDidactica;

/**
 * Console view implementation. Shows a menu and lets the user choose an option to execute.
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
        
        //Space for new convocatoria
        convocatoria.setDatos();
        if(!controller.newConvocatoria(convocatoria)){
            System.out.println("Something went wrong while writing to the file, please try again.");
        }
    }
    // 2
    private void createEnunciado(Controller controller) {
        
    }
    // 3
    private void viewEnunciadoByUD(Controller controller) {
        
    }
    // 4
    /**
     * Asks user for an enunciado ID, then prints the information about the convocatoria associated to the enunciado ID.
     * @param controller Received parameter to connect with class Controller.
     */
    private void viewConvocatoria(Controller controller) {
       List<Enunciado> enunciados = controller.listarEnunciados(null);
       ConvocatoriaExamen convocatoria = new ConvocatoriaExamen();
       Integer idEnunciado;
       Boolean flag = false;
        
        for(Enunciado enunciado:enunciados){
            System.out.println(enunciado.getId());
        }
        System.out.println("Enter the ID of the enunciado used in the convocatoria oyu want to find:");
        idEnunciado = Tool.leerInt();
        for(int i = 0; i < enunciados.length() && flag = false; i++){
            if(enunciados.get(i).getId().equals(idEnunciado)){
                flag = true;
            } 
        }
        if(!flag){
            System.out.println("Enunciado not found, please try again with another.");
        } else{
            convocatoria = controller.searchConvocatoria(idEnunciado);
            if(convocatoria == null){
                System.out.println("Convocatoria not found, please try again.");
            } else{
                convocatoria.getDatos();
            }
        }
    }
    // 5
    /**
     * Lists all enunciados, asks user for enunciado ID, searches for the path to the document in the enunciado object and opens the document.
     * @param controller Received parameter to connect with class Controller.
     */
    public void viewDocument(Controller controller) {
        Integer idEnunciado;
        Boolean flag = false;
        String path = null;
        List<Enunciado> enunciados = controller.listarEnunciados(null);
        
        for(Enunciado enunciado:enunciados){
            System.out.println(enunciado.getId() + "\n");
        }
        System.out.println("Enter the ID of the enunciado associated with the file you want to find:");
        idEnunciado = Tool.leerInt();
        for(int i = 0; i < enunciados.length() && flag = false; i++){
            if(enunciados.get(i).getId().equals(idEnunciado)){
                path = enunciados.get(i).getPath();
                flag = true;
            } 
        }
        if(!flag){
            System.out.println("Enunciado not found, please try again with another.");
        }
        if (path != null && !controller.openDocument(path)) {
            System.out.println("\nFile not found, make sure the enunciado assigned to the file you are searching is correct.\n");
        }
    }
}
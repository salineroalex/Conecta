package view;

import controller.Tool;

/**
 *
 * @author alexs
 */
public class Menu {

    public void menu() {
        Integer choice;
        /*InputStreamReader input =new InputStreamReader(System.in);
	BufferedReader keyboard= new BufferedReader(input);*/
        
        do{
            System.out.println("----------MENÚ----------" +
                                "\n1. Crear una Unidad Didáctica y convocatoria de examen." +
                                "\n2. Crear un enunciado de examen agregando las Unidades Didácticas que va a referir." + 
                                "\n3. Consultar los enunciados de examen en los que se trata una Unidad Didáctica concreta." + 
                                "\n4. Consultar en qué convocatorias se ha utilizado un enunciado concreto." +
                                "\n5. Visualizar el documento de texto asociado a un enunciado." + 
                                "\n6. Salir");
            choice = Tool.leerInt();
            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4: 
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("SALIENDO...");
                    break;
            }
        }while(choice != 6);
    }
    
    
}

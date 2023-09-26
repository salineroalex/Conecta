package reto0conecta;
import controller.Controller;
import view.Menu;

/**
 *
 * @author alexs, iratig
 */
public class Reto0Conecta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Controller controller = new Controller();
        Menu menu = new Menu();
        menu.menu(controller);
    }
    
}

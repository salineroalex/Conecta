/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import controller.DAO;
import controller.DaoDBImplementation;
import model.UnidadDidactica;
import controller.Util;
import exceptions.PersonalizedException;
import java.util.ArrayList;
import java.util.List;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;

/**
 *
 * @author alexs
 */
public class Menu {

    private static Controller controlador = new Controller();

    public Menu(Controller controlador) {
        this.controlador = controlador;
    }

    public static void menu() {
        int numero = 0;

        do {
            System.out.println("Introduce un número");
            numero = Util.leerInt(1, 6);
            switch (numero) {
                case 1:
                    crearUnidad();
                    break;
                case 2:
                    crearEnunciado();
                    break;
                case 3:
                    listarEnunciado();
                    break;

            }
        } while (numero != 7);

    }

    public static void crearUnidad() {
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos();
        if (controlador.addUnidadDidactica(unidadDidactica)) {
            System.out.println("UNIDAD DIDÁCTICA CREADA CORRECTAMENTE)");
        }

    }

    private static void crearEnunciado() {
        Enunciado enunciado = new Enunciado();
        enunciado.setDatos();
        controlador.addEnunciado(enunciado);
    }

    private static void listarEnunciado() {
        String unidadDidactica;
        System.out.println("Insert the educational unit: ");
        unidadDidactica = Util.introducirCadena();
        List<Enunciado> enunciados = controlador.listarEnunciados(unidadDidactica);
        for (Enunciado enunciado : enunciados) {
            System.out.println("ID: " + enunciado.getId());
            System.out.println("Descripción: " + enunciado.getDescripcion());
            System.out.println("Nivel: " + enunciado.getNivelString());
            if(enunciado.getDisponible()== 0){
                System.out.println("Disponible: " + "YES ");
            }else{
                 System.out.println("Disponible: " + "NO");
            }
            System.out.println("Disponible: " + enunciado.getDisponible());
            System.out.println("Ruta: " + enunciado.getRuta());
        }
    }
}

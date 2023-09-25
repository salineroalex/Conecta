package controller;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author alexs, iratig
 */
public class Tool {

    public static int calculoFichero(File fich) {
        int cont = 0;
        if (fich.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(fich);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }
            } catch (EOFException e1) {
                e1.printStackTrace();

            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                ois.close();
                fis.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return cont;
    }

    public static LocalDate leerFechaDMA() {
        boolean error;
        LocalDate date = null;
        String dateString;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato dd/mm/aaaa ");
                error = true;
            }
        } while (error);
        return date;
    }

    public static int leerInt() {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Valor no numérico. Introduce de nuevo:");
                error = true;
            }
        } while (error);
        return num;
    }

    public static String introducirCadena() {
        String cadena = "";
        boolean error;
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        do {
            error = false;
            try {
                cadena = teclado.readLine();
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos.");
                error = true;
            }
        } while (error);
        return cadena;
    }
}

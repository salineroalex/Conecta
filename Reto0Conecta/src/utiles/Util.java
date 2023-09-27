package utiles;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.ConvocatoriaExamen;

public class Util {

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
                System.out.println("-----------");

            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (cont > 0) {
                try {
                    ois.close();
                    fis.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar los flujos");
                }
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
                if (cadena.isEmpty()) {
                    System.out.println("Please enter a value.");
                    error = true;
                }
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            }
        } while (error);
        return cadena;
    }

    public static List volcadoFicheroArrayList(File fich, List<ConvocatoriaExamen> convocatorias) {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(fich));

            ConvocatoriaExamen aux = (ConvocatoriaExamen) ois.readObject();

            while (aux != null) {
                convocatorias.add(aux);
                aux = (ConvocatoriaExamen) ois.readObject();
            }
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            if (convocatorias.size() > 0) {
                ois.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el flujo de entrada.");
        }
        return convocatorias;
    }

    public static void volcadoArrayListAFichero(List<ConvocatoriaExamen> convocatorias, File fich) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(fich));
            for (ConvocatoriaExamen convocatoria : convocatorias) {
                oos.writeObject(convocatoria);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error. Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error en la salida.");
        }
        try {
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el flujo de salida.");
        }
    }
}

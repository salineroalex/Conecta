package controller;

import exceptions.PersonalizedException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.UnidadDidactica;

/**
 * Implementation of DAO interface for file output and input.
 *
 * @author alexs
 */
public class DaoFileImplementation implements DAO {

    private File convocatoriaFile = new File("convocatorias.obj");

    /**
     * Receives the convocatoria and adds it to the "convocatorias.obj" file in
     * the project.
     *
     * @param convocatoria Received convocatoria object to add to the file.
     * @return Returns a boolean in true if the execution was correct; if not,
     * returns false.
     * @throws exceptions.PersonalizedException
     */
    @Override
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) throws PersonalizedException {
        ObjectOutputStream oos = null;
        Boolean okay = false;
        if (convocatoriaFile.exists()) {
            try {
                oos = new MyObjectOutputStream(new FileOutputStream(convocatoriaFile, true));
                okay = true;
            } catch (FileNotFoundException ex) {
                okay = false;
                throw new PersonalizedException("File Not Found!!");
            } catch (IOException ex) {
                okay = false;
                throw new PersonalizedException("Error in File input!!");
            }
        } else {
            try {
                okay = true;
                oos = new ObjectOutputStream(new FileOutputStream(convocatoriaFile));
            } catch (FileNotFoundException ex) {
                okay = false;
                throw new PersonalizedException("File Not Found!!");
            } catch (IOException ex) {
                okay = false;
                throw new PersonalizedException("Error in File input!!");
            }
        }
        try {
            oos.writeObject(convocatoria);
        } catch (IOException ex) {
            okay = false;
            throw new PersonalizedException("Error in File input!!");
        }
        try {
            oos.close();
        } catch (IOException ex) {
            okay = false;
            throw new PersonalizedException("Error colsing output stream!!");
        }
        return okay;
    }

    /**
     * Receives a String and searches for that String in the attribute
     * "idEnunciado" of the convocatoria objects found in the
     * "convocatorias.obj" file, returns the convocatoria found.
     *
     * @param idEnunciado Received Integer used to search for the convocatoria.
     * @return Returns the convocatoria found.
     * @throws exceptions.PersonalizedException
     */
    @Override
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) throws PersonalizedException {
        ConvocatoriaExamen convocatoria = null;
        ConvocatoriaExamen chosen = null;
        Integer length;
        Boolean found = false;

        if (convocatoriaFile.exists()) {
            length = Tool.calculoFichero(convocatoriaFile);
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(convocatoriaFile));
                for (int i = 0; i < length && !found; i++) {
                    convocatoria = (ConvocatoriaExamen) ois.readObject();
                    if (convocatoria.getIdEnunciado().equals(idEnunciado)) {
                        chosen = convocatoria;
                    }
                }
            } catch (FileNotFoundException ex) {
                throw new PersonalizedException("File Not Found!!");
            } catch (IOException ex) {
                throw new PersonalizedException("Error in File input!!");
            } catch (ClassNotFoundException ex) {
                throw new PersonalizedException("Error. Class Not Found!!");
            }
        }
        return chosen;
    }

    /**
     * Searches in the "convocatorias.obj" file for the convocatoria object with
     * the ID received, return the found object.
     *
     * @param id Receives a String used to search for the convocatoria of the
     * same ID attribute.
     * @return The return value is the object convocatoria found.
     * @throws exceptions.PersonalizedException
     */
    public ConvocatoriaExamen searchConvocatoria(String id) throws PersonalizedException {
        ConvocatoriaExamen convocatoria = null;
        ConvocatoriaExamen chosen = null;
        Integer length;
        Boolean found = false;

        if (convocatoriaFile.exists()) {
            length = Tool.calculoFichero(convocatoriaFile);

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(convocatoriaFile));
                for (int i = 0; i < length && !found; i++) {
                    convocatoria = (ConvocatoriaExamen) ois.readObject();
                    if (convocatoria.getConvocatoria().equalsIgnoreCase(id)) {
                        chosen = convocatoria;
                    }
                }
            } catch (FileNotFoundException ex) {
                throw new PersonalizedException("File Not Found!!");
            } catch (IOException ex) {
                throw new PersonalizedException("Error in File input!!");
            } catch (ClassNotFoundException ex) {
                throw new PersonalizedException("Error. Class Not Found!!");
            }
        }
        return chosen;
    }

    /**
     * Gets the id of the enunciado and convocatoria and assignes the enunciado to the convocatoria.
     * @param id            The id of the convocatoria.
     * @param idEnunciado   The id of the enunciado.
     * @return              Returns true if executed correctly.
     * @throws PersonalizedException
     */
    @Override
    public boolean editConvocatoria(String id, Integer idEnunciado) throws PersonalizedException {
        Boolean okay = false;
        ObjectOutputStream oos = null;
        ConvocatoriaExamen convocatoria = null;

        convocatoria = searchConvocatoria(id);
        if (convocatoria != null) {
            convocatoria.setIdEnunciado(idEnunciado);
            if (convocatoriaFile.exists()) {
                try {
                    oos = new MyObjectOutputStream(new FileOutputStream(convocatoriaFile, true));
                    okay = true;
                } catch (FileNotFoundException ex) {
                    okay = false;
                    throw new PersonalizedException("File Not Found!!");
                } catch (IOException ex) {
                    okay = false;
                    throw new PersonalizedException("Error in File input!!");
                }
            } else {
                throw new PersonalizedException("Error. File Not Found!");
            }
            try {
                oos.writeObject(convocatoria);
            } catch (IOException ex) {
                okay = false;
                throw new PersonalizedException("Error in File input!!");
            }
            try {
                oos.close();
            } catch (IOException ex) {
                okay = false;
                throw new PersonalizedException("Error colsing output stream!!");
            }
        } else {
            throw new PersonalizedException("Error. Convocatoria not found!");
        }

        return okay;
    }

    @Override
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException {
        return false;
    }

    @Override
    public Integer crearEnunciado(Enunciado enunciado) throws PersonalizedException {
        return null;
    }

    @Override
    public List<Enunciado> listarEnunciados(String convocataria) throws PersonalizedException {
        return null;
    }

}

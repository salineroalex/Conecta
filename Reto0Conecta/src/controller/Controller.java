package controller;

import exceptions.PersonalizedException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;
import model.UnidadDidactica;

/**
 * The Controller class serves as an intermediary between the application's
 * user interface and the data access layer (DAO). 
 * 
 * @author Irati Garzón, Alex Salinero
 */
public class Controller {
    private static DAO dao;
      /**
     * Adds a new Unidad Didactica to the database.
     * 
     * @param unidadDidactica The Unidad Didactica to add.
     * @return True if the Unidad Didactica was added successfully, false otherwise.
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the operation fails.
     */
    public boolean addUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException{
        dao = DaoFactory.getDB();
        return dao.crearUnidadDidactica(unidadDidactica);
    }
    /**
     * Adds a new Enunciado to the database and associates it with Unidad Didactica entities
     * as specified.
     * 
     * @param enunciado The Enunciado to add.
     * @return An Integer representing the ID of the created Enunciado.
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the database can find the Unidad Didáctica inserted.
     */
    public Integer addEnunciado(Enunciado enunciado) throws PersonalizedException{
        dao = DaoFactory.getDB();
        return dao.crearEnunciado(enunciado);
        
    }
    /**
     * Lists Enunciado objects associated with a specified Convocatoria.
     * 
     * @param checking The Convocatoria for which to list Enunciados.
     * @return A list of Enunciado objects associated with the specified Convocatoria.
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the convocatoria inserted doesn't exits.
     */
     public List<Enunciado> listarEnunciados(String checking ) throws PersonalizedException{
         dao = DaoFactory.getDB();
         return dao.listarEnunciados(checking);
     }
  
    /**
     * Receives a Convocatoria object and writes it in the file.
     * @param convocatoria Received object to write into file.
     * @return             Return boolean to check if the execution was correct.
     */
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) throws PersonalizedException{
        dao = DaoFactory.getFile();
        return dao.newConvocatoria(convocatoria);
    }

    /**
     * Receives an enunciado ID and searches in the convocatoria file for a Convocatoria that has the enunciado ID assigned to it, return the convocatoria found.
     * @param idEnunciado Received Integer parameter to search for a specific convocatoria containing that parameter.
     * @return            Return the convocatoria it searched for.
     */
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) throws PersonalizedException{
        dao = DaoFactory.getFile();
        return dao.searchConvocatoria(idEnunciado);
    }
    
    /**
     * Receives an ID and searches for a convocatoria with athat ID, return the found object.
     * @param id Received String parameter to search for a convocatoria by its own ID.
     * @return   Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(String id) throws PersonalizedException{
        dao = DaoFactory.getFile();
        return dao.searchConvocatoria(id);
    }
    
    /**
     * Receives the path to a file, opens the file in the path.
     * @param path Receives a String parameter to use to open the file in that path.
     * @return     Returns boolean to check if execution was correct.
     */
    public Boolean openDocument(String path) throws PersonalizedException{
        Boolean okay;
        File file = new File(path);
        
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
                okay= true;
            } catch (IOException ex) {
                okay = false;
            }
        }
        else{
            okay = false;
        }
        return okay;
    }
}

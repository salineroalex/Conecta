package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import model.ConvocatoriaExamen;

/**
 * Class that manages connection between view and Data Access Objects.
 * @author alexs, iratig
 */
public class Controller {

    private static DAO dao;

    /**
     * Receives a Convocatoria object and writes it in the file.
     * @param convocatoria Received object to write into file.
     * @return             Return boolean to check if the execution was correct.
     */
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) {
        dao = DaoFactory.getFile();
        return dao.newConvocatoria(convocatoria);
    }

    /**
     * Receives an enunciado ID and searches in the convocatoria file for a Convocatoria that has the enunciado ID assigned to it, return the convocatoria found.
     * @param idEnunciado Received Integer parameter to search for a specific convocatoria containing that parameter.
     * @return            Return the convocatoria it searched for.
     */
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) {
        dao = DaoFactory.getFile();
        return dao.searchConvocatoria(idEnunciado);
    }
    
    /**
     * Receives an ID and searches for a convocatoria with athat ID, return the found object.
     * @param id Received String parameter to search for a convocatoria by its own ID.
     * @return   Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(String id) {
        dao = DaoFactory.getFile();
        return dao.searchConvocatoria(id);
    }
    
    /**
     * Receives the path to a file, opens the file in the path.
     * @param path Receives a String parameter to use to open the file in that path.
     * @return     Returns boolean to check if execution was correct.
     */
    public Boolean openDocument(String path) {
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

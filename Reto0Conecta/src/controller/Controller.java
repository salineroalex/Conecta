package controller;

import exceptions.PersonalizedException;
import java.util.List;
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
}

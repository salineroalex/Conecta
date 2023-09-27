package controller;

import exceptions.PersonalizedException;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.UnidadDidactica;

/**
 * This interface defines the contract for performing
 * database and file operations. 
 * @author  Irati Garzón, Olivia Salinero
 */
public interface DAO {
     /**
     * Creates a new Unidad Didactica in the database with the provided information.
     * 
     * @param unidadDidactica The Unidad Didactica to create.
     * @return True if the Unidad Didactica was created successfully, false otherwise.
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the operation fails.
     */
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException;
       /**
     * Creates a new Enunciado in the database with the provided information and
     * associates it with Unidad Didactica entities as specified.
     * 
     * @param enunciado The Enunciado to create.
     * @return An Integer representing the ID of the created Enunciado.
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the operation fails.
     */
    public Integer crearEnunciado(Enunciado enunciado) throws PersonalizedException;
     /**
     * Retrieves a list of Enunciado objects associated with a specified Convocatoria.
     * 
     * @param convocatoria The Convocatoria to filter Enunciados.
     * @return A list of Enunciado objects associated with the specified Convocatoria or a list of all Enunciados if the string is null
     * @throws PersonalizedException If an error occurs during the database operation
     *                              or if the operation fails.
     */
    public List<Enunciado> listarEnunciados(String convocataria) throws PersonalizedException;
    
    //File
    /**
     * Creates a new convocatoria and returns a boolean if the execution was correct.
     * @param convocatoria Receives parameter convocatoria of Object type ConvocatoriaExamen and stores its values persistently.
     * @return             Returns boolean to check if execution was correct.
     */
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) throws PersonalizedException;
    /**
     * Searches for a convocatoria object with the received enunciado ID assigned to it.
     * @param idEnunciado Receives de ID of an enunciado object to search a convocatoria.
     * @return            Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) throws PersonalizedException;
    /**
     * Searches a convocatoria by its ID.
     * @param id Receives parameter ID to search the convocatoria with the same ID.
     * @return   Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(String id) throws PersonalizedException;
    
    /**
     * Gets the id of the enunciado and convocatoria and assignes the enunciado to the convocatoria.
     * @param id            The id of the convocatoria.
     * @param idEnunciado   The id of the enunciado.
     * @return              Returns true if executed correctly.
     * @throws PersonalizedException 
     */
    public boolean editConvocatoria (String id, Integer idEnunciado) throws PersonalizedException;
}

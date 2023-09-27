package controller;

import exceptions.PersonalizedException;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;
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
    
}

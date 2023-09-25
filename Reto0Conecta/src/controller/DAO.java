package controller;

import model.ConvocatoriaExamen;

/**
 * Implementable interface to create and search or read Data Access Objects.
 * @author alexs, iratig
 */
public interface DAO {
    //DB
    
    
    //File
    /**
     * Creates a new convocatoria and returns a boolean if the execution was correct.
     * @param convocatoria Receives parameter convocatoria of Object type ConvocatoriaExamen and stores its values persistently.
     * @return             Returns boolean to check if execution was correct.
     */
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria);
    /**
     * Searches for a convocatoria object with the received enunciado ID assigned to it.
     * @param idEnunciado Receives de ID of an enunciado object to search a convocatoria.
     * @return            Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado);
    /**
     * Searches a convocatoria by its ID.
     * @param id Receives parameter ID to search the convocatoria with the same ID.
     * @return   Returns the convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(String id);
}

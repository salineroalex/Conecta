package controller;

import model.ConvocatoriaExamen;
import model.Enunciado;

/**
 *
 * @author alexs, iratig
 */
public interface DAO {
    //DB
    
    
    //File
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria);
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado);
    
}

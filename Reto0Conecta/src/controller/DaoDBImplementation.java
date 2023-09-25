package controller;

import model.ConvocatoriaExamen;

/**
 *
 * @author alexs, iratig
 */
public class DaoDBImplementation implements DAO{

    @Override
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) {
        return false;
    }

    @Override
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) {
        return null;
    }

    @Override
    public ConvocatoriaExamen searchConvocatoria(String id) {
        return null;
    }
}

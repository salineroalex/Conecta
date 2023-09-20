package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConvocatoriaExamen;

/**
 *
 * @author alexs, iratig
 */
public class DaoFileImplementation implements DAO{
    private File convocatoriaFile = new File("convocatorias.obj");
    
    @Override
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria){
        ObjectOutputStream oos = null;
        Boolean okay = false;
        if(convocatoriaFile.exists()){
            try {
                oos = new MyObjectOutputStream(new FileOutputStream(convocatoriaFile, true));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                oos = new ObjectOutputStream(new FileOutputStream(convocatoriaFile));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            oos.writeObject(convocatoria);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return okay;
    }
    
    @Override
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado){
        ConvocatoriaExamen convocatoria = null;
        ConvocatoriaExamen chosen = null;
        Integer length;
        Boolean found = false;
        
        if(convocatoriaFile.exists()){
            length = Tool.calculoFichero(convocatoriaFile);            
            
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(convocatoriaFile));
                for(int i = 0; i < length && !found; i++){
                    convocatoria = (ConvocatoriaExamen) ois.readObject();
                    if(convocatoria.getIdEnunciado().equals(idEnunciado)){
                        chosen = convocatoria;
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return chosen;
    }
    
    public ConvocatoriaExamen searchConvocatoria(String id){
        return null;
    }
}

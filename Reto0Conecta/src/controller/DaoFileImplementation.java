package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.ConvocatoriaExamen;

/**
 * Implementation of DAO interface for file output and input.
 * @author alexs
 */
public class DaoFileImplementation implements DAO{
    private File convocatoriaFile = new File("convocatorias.obj");
    
    /**
     * Receives the convocatoria and adds it to the "convocatorias.obj" file in the project.
     * @param convocatoria Received convocatoria object to add to the file.
     * @return             Returns a boolean in true if the execution was correct; if not, returns false.
     */
    @Override
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria){
        ObjectOutputStream oos = null;
        Boolean okay = false;
        if(convocatoriaFile.exists()){
            try {
                okay = true;
                oos = new MyObjectOutputStream(new FileOutputStream(convocatoriaFile, true));
            } catch (FileNotFoundException ex) {
                //ex.printStackTrace();
                okay = false;
            } catch (IOException ex) {
                //ex.printStackTrace();
                okay = false;
            }
        }else{
            try {
                okay = true;
                oos = new ObjectOutputStream(new FileOutputStream(convocatoriaFile));
            } catch (FileNotFoundException ex) {
                //ex.printStackTrace();
                okay = false;
            } catch (IOException ex) {
                //ex.printStackTrace();
                okay = false;
            }
        }
        try {
            oos.writeObject(convocatoria);
        } catch (IOException ex) {
            //ex.printStackTrace();
            okay = false;
        }
        try {
            oos.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
            okay = false;
        }
        return okay;
    }
    
    /**
     * Receives a String and searches for that String in the attribute "idEnunciado" of the convocatoria objects found in the "convocatorias.obj" file, returns the convocatoria found.
     * @param idEnunciado Received Integer used to search for the convocatoria.
     * @return            Returns the convocatoria found.
     */
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
    
    /**
     * Searches in the "convocatorias.obj" file for the convocatoria object with the ID received, return the found object.
     * @param id Receives a String used to search for the convocatoria of the same ID attribute.
     * @return   The return value is the object convocatoria found.
     */
    public ConvocatoriaExamen searchConvocatoria(String id){
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
                    if(convocatoria.getConvocatoria().equalsIgnoreCase(id)){
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
}

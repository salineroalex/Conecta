package controller;

/**
 *
 * @author alexs, iratig
 */
public class DaoFactory {
    public static DAO getDB(){
        return new DaoDBImplementation();
    }
    
    public static DAO getFile(){
        return new DaoFileImplementation();
    }
}

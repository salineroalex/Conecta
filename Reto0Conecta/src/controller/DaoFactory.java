/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author alexs, iratig
 */
public class DaoFactory {
    
   /* public static DAO getFile(){
        return new DaoFileImplementation();
    } */
    
    public static DAO getDB(){
        return new DaoDBImplementation();
    }
}

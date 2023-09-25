/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.PersonalizedException;
import java.util.List;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;
import model.UnidadDidactica;

/**
 *
 * @author alexs, iratig
 */
public class Controller {
    private static DAO dao;
    public boolean addUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException{
        dao = DaoFactory.getDB();
        return dao.crearUnidadDidactica(unidadDidactica);
    }
    public Integer addEnunciado(Enunciado enunciado) throws PersonalizedException{
        dao = DaoFactory.getDB();
        return dao.crearEnunciado(enunciado);
        
    }
     public List<Enunciado> listarEnunciados(String checking ) throws PersonalizedException{
         dao = DaoFactory.getDB();
         return dao.listarEnunciados(checking);
     }
}

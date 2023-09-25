/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.PersonalizedException;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.ResultadoCreacionEnunciado;
import model.UnidadDidactica;

/**
 *
 * @author alexs, iratig
 */
public interface DAO {
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException;
   
    public Integer crearEnunciado(Enunciado enunciado) throws PersonalizedException;
    public List<Enunciado> listarEnunciados(String convocataria) throws PersonalizedException;
    
}

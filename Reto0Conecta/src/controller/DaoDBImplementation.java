/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.UnidadDidactica;
import controller.ConnectionOpenClose;
import exceptions.PersonalizedException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ResultadoCreacionEnunciado;

/**
 * This class represents the implementation of a Data Access Object (DAO) for
 * database operations. It provides methods for creating educational units and
 * managing enunciados.
 *
 * @author Irati Garzón
 */
public class DaoDBImplementation implements DAO {

    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose connection;
    private ResultSet rs;

    @Override
    /**
     * Creates a new educational unit (unidad didáctica) in the database with
     * the provided information.
     *
     * @param unidadDidactica The educational unit to create.
     * @return True if the educational unit was created successfully, false
     * otherwise.
     * @throws PersonalizedException If an error occurs during the database
     * operation or if the operation fails.
     */
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
        boolean correct = false;
        final String INSERTUnidadDidactica = "INSERT INTO unidad (acronimo, titulo, evaluacion, descripcion) VALUES ( ?, ?, ?, ?)";

        try {
            //Open the connection 
            connection = new ConnectionOpenClose();
            con = connection.openConnection();
            //Establish the statatenent
            stmt = con.prepareStatement(INSERTUnidadDidactica);
            stmt.setString(1, unidadDidactica.getAcronimo());
            stmt.setString(2, unidadDidactica.getTitulo());
            stmt.setString(3, unidadDidactica.getEvaluacion());
            stmt.setString(4, unidadDidactica.getDescripcion());

            if (stmt.executeUpdate() >= 0) {
                correct = true;
            }

            connection.closeConnection(stmt, con);
        } catch (SQLException ex) {
           // ADD
        }

        return correct;

    }

    /**
     * Creates a new enunciado in the database with the provided information and
     * associates it with educational units as specified.
     *
     * @param enunciado The enunciado to create.
     * @return A ResultadoCreacionEnunciado object containing information about
     * the creation process.
     */
    @Override
    public Integer crearEnunciado(Enunciado enunciado) {
        //INCLUIR VALIDACIÓN --> CLASE QUE LO CONTROLE TODO
        Integer id = 0;
        List<Integer> idUnidades = new ArrayList<>();

        final String INSERTnunciado = "INSERT INTO enunciado (descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?)";
        final String SELECTid = "SELECT MAX(id) FROM enunciado";
        final String SelectidUnidades = "SELECT id FROM unidad WHERE acronimo = ?";
        final String INSERTUnidad_Enunciado = "INSERT INTO unidad_enunciado(unidads_id, enunciados_id) VALUES(?,?)";
        try {
            connection = new ConnectionOpenClose();
            con = connection.openConnection();
            //INSERT ENUNCIADO 
            stmt = con.prepareStatement(INSERTnunciado);

            stmt.setString(1, enunciado.getDescripcion());
            stmt.setString(2, enunciado.getNivelString());
            stmt.setInt(3, enunciado.getDisponible());
            stmt.setString(4, enunciado.getRuta());

            stmt.executeUpdate();

            //ESTA COMPROBACIÓN ES PARA VER SI EL USUARIO HA QUERIDO INSERTAR UNIDADES DIDÁCTICAS
            //BUSCAR EL ID DEL ULTIMO ENUNCIADO INSERTADO YA QUE EL ID SE AUTO GENERA EN LA BASE DE DATOS
            stmt = con.prepareStatement(SELECTid);
            rs = stmt.executeQuery();

            //LO DEVUELVE Y LO GUARDAMOS 
            while (rs.next()) {
                id = rs.getInt(1);
            }
            if (enunciado.getUnidadesDidacticas().size() > 0) {
                //BUSCAMOS EL ID TENIENDO EN CUENTA EL ACRÓNIMO LO TRAEMOS EN EL ARRAY PORQUE UN ENUNCIADO PUEDE ESTAR EN MÁS DE UNA UNIDAD DIDÁCTICA
                stmt = con.prepareStatement(SelectidUnidades);
                for (int i = 0; i < enunciado.getUnidadesDidacticas().size(); i++) {
                    stmt.setString(1, enunciado.getUnidadesDidacticas().get(i));
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        idUnidades.add(rs.getInt("id"));
                    }

                }

                if (idUnidades.size() > 0) {

                    //INSERTAR ESE ID DEL ENUNCIADO Y ES ID DE UNIDAD DIDÁCTICA
                    stmt = con.prepareStatement(INSERTUnidad_Enunciado);
                    for (int i = 0; i < enunciado.getUnidadesDidacticas().size(); i++) {
                        stmt.setInt(1, idUnidades.get(i));
                        stmt.setInt(2, id);
                        stmt.executeUpdate();

                    }
                }

            } 
            connection.closeConnection(stmt, con);

        } catch (SQLException ex) {
            Logger.getLogger(DaoDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    /**
     * Retrieves a list of enunciados associated with a specified convocatoria.
     *
     * @param convocatoria The convocatoria to filter enunciados.
     * @return A list of Enunciado objects associated with the specified
     * convocatoria.
     */
    @Override
    public List<Enunciado> listarEnunciados(String checking) {
        final String SelectidUnidades = "SELECT id FROM unidad WHERE acronimo = ?";
        final String SelectidEnunciado = "SELECT enunciados_id FROM unidad_enunciado WHERE unidads_id = ?";
        final String SelectEnunciadoID = "Select * FROM enunciado where id = ?";
        final String SelectEnunciado = "Select * FROM enunciado";

        int idUnidad = 0;
        List<Integer> idEnunciados = new ArrayList<>();
        List<Enunciado> enunciados = new ArrayList<>();
        Enunciado enunciado = new Enunciado();
        try {
            connection = new ConnectionOpenClose();
            //AÑADIR SI LO QUEREMOS PARA EL MÉTODO DE LA CONVOCATORIA O SOLO DEVOLVER ENUNCIADO
            con = connection.openConnection();
            if (checking != null) {
                stmt = con.prepareStatement(SelectidUnidades);
                stmt.setString(1, checking);

                rs = stmt.executeQuery();

                while (rs.next()) {
                    idUnidad = rs.getInt("id");
                }

                stmt = con.prepareStatement(SelectidEnunciado);
                stmt.setInt(1, idUnidad);

                rs = stmt.executeQuery();
                while (rs.next()) {
                    idEnunciados.add(rs.getInt(1));
                }

                stmt = con.prepareStatement(SelectEnunciadoID);

                for (int i = 0; i < idEnunciados.size(); i++) {
                    stmt.setInt(1, idEnunciados.get(i));
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        enunciado.setId(rs.getInt("id"));
                        enunciado.setDescripcion(rs.getString("descripcion"));
                        enunciado.setNivelString(rs.getString("nivel"));
                        enunciado.setDisponible(rs.getInt("disponible"));
                        enunciado.setRuta(rs.getString("ruta"));
                        enunciados.add(enunciado);
                    }
                }
            } else {
                stmt = con.prepareStatement(SelectEnunciado);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    enunciado.setId(rs.getInt("id"));
                    enunciado.setDescripcion(rs.getString("descripcion"));
                    enunciado.setNivelString(rs.getString("nivel"));
                    enunciado.setDisponible(rs.getInt("disponible"));
                    enunciado.setRuta(rs.getString("ruta"));
                    enunciados.add(enunciado);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoDBImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enunciados;
    }
   
}

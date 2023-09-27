package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.ConvocatoriaExamen;
import model.Enunciado;
import model.UnidadDidactica;
import exceptions.PersonalizedException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) throws PersonalizedException {
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
            throw new PersonalizedException(ex.getMessage());
        }

        return correct;

    }

    /**
     * Creates a new enunciado in the database with the provided information and
     * associates it with educational units as specified.
     *
     * @param enunciado The enunciado to create.
     * @return a Integer with the id of the enunciado.
     * @throws PersonalizedException If an error occurs during the database
     * operation or if there is no education unit to insert.
     */
    @Override
    public Integer crearEnunciado(Enunciado enunciado) throws PersonalizedException {
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
            // This check is to determine if the user has wanted to insert educational units
            // Find the ID of the last inserted enunciado since the ID is auto-generated in the database
            if (enunciado.getUnidadesDidacticas().size() > 0) {
                //We look for the ID considering the acronym and bring it into the array because an enunciado can be in more than one educational unit.
                stmt = con.prepareStatement(SelectidUnidades);
                for (int i = 0; i < enunciado.getUnidadesDidacticas().size(); i++) {
                    stmt.setString(1, enunciado.getUnidadesDidacticas().get(i));
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        idUnidades.add(rs.getInt("id"));
                    }

                }
                //This is to check if there are any idUnidades. Perhaps the user has inserted the wrong acronym.
                if (idUnidades.size() > 0) {

                    //INSERTAR ESE ID DEL ENUNCIADO Y ES ID DE UNIDAD DIDÁCTICA
                    stmt = con.prepareStatement(INSERTUnidad_Enunciado);
                    for (int i = 0; i < enunciado.getUnidadesDidacticas().size(); i++) {
                        stmt.setInt(1, idUnidades.get(i));
                        stmt.setInt(2, id);
                        stmt.executeUpdate();

                    }
                } else {
                    throw new PersonalizedException("Not educational Unit found");
                }

            }
            connection.closeConnection(stmt, con);

        } catch (SQLException ex) {
            throw new PersonalizedException(ex.getMessage());
        }
        return id;
    }

    /**
     * Retrieves a list of enunciados associated with a specified convocatoria.
     *
     * @param checking The checking parameter (may be null).
     * @return A list of Enunciado objects associated with the specified
     * convocatoria.
     * @throws PersonalizedException If an error occurs during the database
     * operation or if the operation fails.
     */
    @Override
    public List<Enunciado> listarEnunciados(String checking) throws PersonalizedException {
        final String SelectEnunciadoID = "SELECT * FROM enunciado WHERE id IN (SELECT enunciados_id FROM unidad_enunciado WHERE unidads_id = (SELECT id FROM unidad WHERE acronimo = ?))";
        final String SelectEnunciado = "Select * FROM enunciado";

        List<Enunciado> enunciados = new ArrayList<>();
        try {
            connection = new ConnectionOpenClose();
            con = connection.openConnection();
            //Check whether it is null or not, so this method can be used to search by Unidad Didáctica or just to obtain all the enunciados.
            if (checking != null) {
                stmt = con.prepareStatement(SelectEnunciadoID);
                stmt.setString(1, checking);
            } else {
                stmt = con.prepareStatement(SelectEnunciado);
            }
            rs = stmt.executeQuery();
            //Obteins the resultset and creates enunciado
            while (rs.next()) {
                Enunciado enunciado = new Enunciado();
                enunciado.setId(rs.getInt("id"));
                enunciado.setDescripcion(rs.getString("descripcion"));
                enunciado.setNivelString(rs.getString("nivel"));
                enunciado.setDisponible(rs.getInt("disponible"));
                enunciado.setRuta(rs.getString("ruta"));
                enunciados.add(enunciado);

            }
            //If the user sends a educational unit that doesn't exits.
            if (enunciados.isEmpty() && checking != null) {
                throw new PersonalizedException("Educational Unit not found");
            }

        } catch (SQLException ex) {
            throw new PersonalizedException(ex.getMessage());
        }

        return enunciados;
    }

    @Override
    public boolean newConvocatoria(ConvocatoriaExamen convocatoria) throws PersonalizedException {
        return false;
    }

    @Override
    public ConvocatoriaExamen searchConvocatoria(Integer idEnunciado) throws PersonalizedException {
        return null;
    }

    @Override
    public ConvocatoriaExamen searchConvocatoria(String id) throws PersonalizedException {
        return null;
    }

    @Override
    public boolean editConvocatoria(String id, Integer idEnunciado) throws PersonalizedException {
        return false;
    }

}

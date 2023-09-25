package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class handles the opening and closing of database connections using
 * MySQL. It retrieves the database connection information from a
 * "config.properties" file and provides methods to open and close connections.
 *
 * @author Irati Garzón
 */
class ConnectionOpenClose {

    private ResourceBundle configFile;
    private String url;
    private String user;
    private String pass;

    /**
     * Constructs a new ConnectionOpenClose instance. It initializes the
     * database connection parameters by reading them from the
     * "config.properties" file.
     */
    public ConnectionOpenClose() {
        configFile = ResourceBundle.getBundle("controller.Config");
        url = configFile.getString("URL");
        user = configFile.getString("USER");
        pass = configFile.getString("PASSWORD");
    }

    /**
     * Opens a database connection to the MySQL database using the connection
     * parameters obtained from the "config.properties" file.
     *
     * @return A valid database connection if successful, or null if an error
     * occurs.
     * @throws SQLException If an SQL exception occurs during the connection
     * process.
     */
    public Connection openConnection() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return con;
    }

    /**
     * Closes a database connection and a prepared statement if they are not
     * null.
     *
     * @param stmt The prepared statement to close.
     * @param con The database connection to close.
     * @throws SQLException If an SQL exception occurs during the closing
     * process.
     */
    public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}

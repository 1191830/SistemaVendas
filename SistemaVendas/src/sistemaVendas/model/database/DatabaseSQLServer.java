package sistemaVendas.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class DatabaseSQLServer implements Database {

    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=SistemaVendas", "eu","atec123");
            return this.connection;
        } catch (Exception ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

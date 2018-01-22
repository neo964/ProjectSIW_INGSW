package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DataSource {
	final private String dbURI;
	final private String username;
	final private String password;
	
	public DataSource (String _dbURI, String _username, String _password){
		dbURI = _dbURI;
		username = _username;
		password = _password;
	}
	
	public Connection getConnection () throws PersistenceException{
		Connection connection = null;
		try {
		    connection = DriverManager.getConnection(dbURI,username, password);
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}

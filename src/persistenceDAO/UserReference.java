package persistenceDAO;

import java.sql.*;

import Database.DataSource;
import Database.PersistenceException;
import Model.User;

public class UserReference extends User{
	private DataSource dataSource;

	public UserReference(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public String getPassword(){						
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from user where E-Mail = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, this.getEmail());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return result.getString("Password");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return null;
	}

}

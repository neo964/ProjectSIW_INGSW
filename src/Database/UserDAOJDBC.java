package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Model.User;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public class UserDAOJDBC implements UserDAO {

	private DataSource dataSource;
	
	public UserDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(User user) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"User\" (\"Password\", \"E-Mail\", \"Premium\", \"Admin\", \"Date\", \"FirstName\", \"LastName\", \"Image\") values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user.getPassword ());
			statement.setString(2, user.getEmail());
			statement.setBoolean(3, user.isPremium());
			statement.setBoolean(4, user.isAdmin());
			long secs = user.getDateOfBirth().getTime();
			statement.setDate(5, new java.sql.Date(secs));
			statement.setString(6, user.getFirstName());
			statement.setString(7, user.getLastName());
			statement.setString(8, user.getPathToImage());
			statement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
			AddressDAOJDBC address = new AddressDAOJDBC(dataSource);
			address.save(user.getAddress());
			PaymentMethodDAOJDBC payment = new PaymentMethodDAOJDBC(dataSource);
			payment.save(user.getPaymentMethod());
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public User findByPrimaryKey(String name) {
		Connection connection = this.dataSource.getConnection();
		User user = null;
		try { 
			PreparedStatement statement;
			String query = "select * from \"User\" WHERE \"E-Mail\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user = new User(result.getString("FirstName"), result.getString("LastName"), result.getString("E-Mail"), null, null, result.getBoolean("Premium"), result.getBoolean("Admin"), result.getDate("Date"), result.getString("Image"));
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
		return user;
	}

	@Override
	public List<User> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<User> users = new java.util.LinkedList<>();
		try {
			User user;
			PreparedStatement statement;
			String query = "select * from \"User\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user = new User(result.getString("FirstName"), result.getString("LastName"), result.getString("E-Mail"), null, null, result.getBoolean("Premium"), result.getBoolean("Admin"), result.getDate("Date"), result.getString("Image"));
				users.add(user);
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
		return users;
	}

	@Override
	public void update(User user) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String update = "update \"User\" SET \"E-Mail\" = ?, \"Premium\" = ?, \"Admin\" = ?, \"Date\" = ?, \"FirstName\" = ?, \"LastName\" = ?, \"Image\" = ? WHERE \"E-Mail\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getEmail());
			statement.setBoolean(2, user.isPremium());
			statement.setBoolean(3, user.isAdmin());
			long secs = user.getDateOfBirth().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			statement.setString(5, user.getFirstName());
			statement.setString(6, user.getLastName());
			statement.setString(7, user.getPathToImage());
			statement.setString(8, user.getEmail());
			statement.executeUpdate();
			AddressDAOJDBC address = new AddressDAOJDBC(dataSource);
			address.update(user.getAddress());
			PaymentMethodDAOJDBC payment = new PaymentMethodDAOJDBC(dataSource);
			payment.update(user.getPaymentMethod());
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(User user) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String delete = "delete FROM \"User\" WHERE \"E-Mail\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, user.getEmail());
			statement.executeUpdate();
			AddressDAOJDBC address = new AddressDAOJDBC(dataSource);
			address.delete(user.getAddress());
			PaymentMethodDAOJDBC payment = new PaymentMethodDAOJDBC(dataSource);
			payment.delete(user.getPaymentMethod());
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void setPassword(User user, String password) {
		Connection connection = this.dataSource.getConnection();
		try { 
			connection.setAutoCommit(false);
			String update = "update \"User\" SET \"Password\" = ?, \"E-Mail\" = ?, \"Premium\" = ?, \"Admin\" = ?, \"Date\" = ?, \"FirstName\" = ?, \"LastName\" = ? WHERE \"E-Mail\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getPassword ());
			statement.setString(2, user.getEmail());
			statement.setBoolean(3, user.isPremium());
			statement.setBoolean(4, user.isAdmin());
			long secs = user.getDateOfBirth().getTime();
			statement.setDate(5, new java.sql.Date(secs));
			statement.setString(6, user.getFirstName());
			statement.setString(7, user.getLastName());
			statement.setString(8, user.getEmail());
			statement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public UserReference findByPrimaryKeyCredential(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

package Database;

import java.sql.*;
import java.util.List;

import Model.Address;
import persistenceDAO.AddressDAO;

public class AddressDAOJDBC implements AddressDAO {

	private DataSource dataSource;
	
	public AddressDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Address address) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"Address\" (\"Street\", \"Country\", \"ZipCode\", \"District\", \"User\") values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, address.getStreet());
			statement.setString(2, address.getCountry());
			statement.setInt(3, address.getZipcode());
			statement.setString(4, address.getDistrict());
			statement.setString(5, address.getUser());
			statement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(false);
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
	public Address findByPrimaryKey(String street, int zipcode, String username) {
		Connection connection = this.dataSource.getConnection();
		Address address = null;
		try {
			PreparedStatement statement;
			String query = "select * from \"Address\" where \"Street\" = ? AND \"ZipCode\" = ? AND \"User\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, street);
			statement.setInt(2, zipcode);
			statement.setString(3, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				address = new Address(result.getString("Street"), result.getString("Country"), result.getString("District"), result.getString("User"), result.getInt("ZipCode"));
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
		return address;
	}

	@Override
	public List<Address> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Address> addresses = new java.util.LinkedList<Address>();
		try {
			Address address;
			PreparedStatement statement;
			String query = "select * from \"Address\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				address = new Address(result.getString("Street"), result.getString("Country"), result.getString("District"), result.getString("user"), result.getInt("ZipCode"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return addresses;
	}

	@Override
	public List<Address> findMyAddresses(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Address> addresses = new java.util.LinkedList<Address>();
		try {
			Address address;
			PreparedStatement statement;
			String query = "select * from \"Address\" where \"User\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				address = new Address(result.getString("Street"), result.getString("Country"), result.getString("District"), result.getString("user"), result.getInt("ZipCode"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return addresses;
	}

	
	@Override
	public void update(Address address) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update \"Address\" SET \"Street\" = ?, \"Country\" = ?, \"ZipCode\" = ?, \"District\" = ?, \"User\" = ? WHERE \"Street\" = ? AND \"ZipCode\" = ? AND \"User\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, address.getStreet());
			statement.setString(2, address.getCountry());
			statement.setInt(3, address.getZipcode());
			statement.setString(4, address.getDistrict());
			statement.setString(5, address.getUser());
			statement.setString(6, address.getStreet());
			statement.setInt(7, address.getZipcode());
			statement.setString(8, address.getUser());
			statement.executeUpdate();
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
	public void delete(Address address) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM \"Address\" WHERE \"Street\" = ? AND \"ZipCode\" = ? AND \"User\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, address.getStreet());
			statement.setInt(2, address.getZipcode());
			statement.setString(3, address.getUser());
			statement.executeUpdate();
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
	public void deleteAllOfUser(String user) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM \"Address\" WHERE \"User\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, user);
			statement.executeUpdate();
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

}

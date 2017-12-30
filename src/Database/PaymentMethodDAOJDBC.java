package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Model.PaymentMethod;
import persistenceDAO.PaymentMethodDAO;

public class PaymentMethodDAOJDBC implements PaymentMethodDAO {

	private DataSource dataSource;
	
	public PaymentMethodDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(PaymentMethod paymentMethod) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into \"PaymentMethod\" (\"CardNumber\", \"User\", \"Code\", \"ExpirationDate\") values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, paymentMethod.getCardNumber());
			statement.setString(2, paymentMethod.getUser ());
			statement.setInt(3, paymentMethod.getCode());
			long secs = paymentMethod.getExpirationDate().getTime();
			statement.setDate(4, new java.sql.Date(secs));
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
	public PaymentMethod findByPrimaryKey(String CardNumber, String User) {
		Connection connection = this.dataSource.getConnection();
		PaymentMethod paymentMethod = null;
		try {
			PreparedStatement statement;
			String query = "select * from \"PaymentMethod\" where \"CardNumber\" = ? AND \"User\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, CardNumber);
			statement.setString(2, User);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				paymentMethod = new PaymentMethod(result.getString("CardNumber"), result.getString("User"), result.getInt("Code"), result.getDate("ExpirationDate"));
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
		return paymentMethod;
	}

	@Override
	public List<PaymentMethod> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<PaymentMethod> paymentMethods = new LinkedList<>();
		try {
			PaymentMethod paymentMethod = null;
			PreparedStatement statement;
			String query = "select * from \"PaymentMethod\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				paymentMethod = new PaymentMethod(result.getString("CardNumber"), result.getString("User"), result.getInt("Code"), result.getDate("ExpirationDate"));
				paymentMethods.add(paymentMethod);
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
		return paymentMethods;
	}
	


	@Override
	public List<PaymentMethod> findAll(String user) {
		Connection connection = this.dataSource.getConnection();
		List<PaymentMethod> paymentMethods = new LinkedList<>();
		try {
			PaymentMethod paymentMethod = null;
			PreparedStatement statement;
			String query = "select * from \"PaymentMethod\" where \"User\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				paymentMethod = new PaymentMethod(result.getString("CardNumber"), result.getString("User"), result.getInt("Code"), result.getDate("ExpirationDate"));
				paymentMethods.add(paymentMethod);
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
		return paymentMethods;
	}

	@Override
	public void update(PaymentMethod paymentMethod) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String update = "update \"PaymentMethod\" SET \"CardNumber\" = ?, \"User\" = ?, \"Code\" = ?, \"ExpirationDate\" = ? WHERE \"CardNumber\" = ? AND \"User\" = ?";
			statement = connection.prepareStatement(update);
			statement.setString(1, paymentMethod.getCardNumber());
			statement.setString(2, paymentMethod.getUser());
			statement.setInt(3, paymentMethod.getCode());
			long secs = paymentMethod.getExpirationDate().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			statement.setString(5, paymentMethod.getCardNumber());
			statement.setString(6, paymentMethod.getUser());
			
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
	public void delete(PaymentMethod paymentMethod) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM \"PaymentMethod\" WHERE \"CardNumber\" = ? AND \"User\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, paymentMethod.getCardNumber());
			statement.setString(2, paymentMethod.getUser());
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
			String delete = "delete FROM \"PaymentMethod\" WHERE \"User\" = ?";
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

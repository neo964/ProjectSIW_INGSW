package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.Advice;
import Model.Film;
import Model.Friendship;
import Model.Multimedia;
import Model.User;
import persistenceDAO.AdviceDAO;

public class AdviceDAOJDBC implements AdviceDAO {


	private DataSource dataSource;
	
	public AdviceDAOJDBC(DataSource data) {
		this.dataSource = data;
	}
	
	
	@Override
	public void save(Advice advice) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"Advice\"(\"Adviser\", \"Advisor\", \"isFilm\", \"IDFilm\", \"IDTVSerie\") values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, advice.getAdvicer().getEmail());
			statement.setString(2, advice.getAdvisor().getEmail());
			if (advice.getMultimedia() instanceof Film) {
				statement.setBoolean(3, true);
				statement.setInt(4, advice.getMultimedia().getId());
				statement.setInt(5, -1);
			} else {
				statement.setBoolean(3, false);
				statement.setInt(5, advice.getMultimedia().getId());
				statement.setInt(4, -1);
			}
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
	public Advice findByPrimaryKey(String friend1, String friend2, int idmultimedia, boolean isFilm) {
		Connection connection = this.dataSource.getConnection();
		Advice advice = null;
		try {
			PreparedStatement statement;
			String query;
			if (isFilm)
				query = "select * from \"Advice\" where \"Adviser\" = ? AND \"Advisor\" = ? And \"IDFilm\" = ?";
			else
				query = "select * from \"Advice\" where \"Adviser\" = ? AND \"Advisor\" = ? And \"IDTVSerie\" = ?";
				
			statement = connection.prepareStatement(query);
			statement.setString (1, friend1);
			statement.setString (2, friend2);
			statement.setInt (3, idmultimedia);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				User user1 = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(result.getString("Adviser"));
				User user2 = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(result.getString("Advisor"));
				Multimedia multimedia = null;
				if (isFilm) {
					multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(idmultimedia);
				} else {
					multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(idmultimedia);
				}
				advice = new Advice(user1, user2, multimedia);
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
		return advice;
	}

	@Override
	public List<Advice> findAllMyAdvice(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Advice> advices = new LinkedList<>();
		try {
			Advice advice = null;
			PreparedStatement statement;
			String query = "select * from \"Advice\" where \"Advisor\" = ?";
				
			statement = connection.prepareStatement(query);
			statement.setString (1, user);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				User user1 = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(result.getString("Adviser"));
				User user2 = DatabaseManager.getInstance().getDaoFactory().getUserDAO().findByPrimaryKey(result.getString("Advisor"));
				Multimedia multimedia = null;
				if (result.getBoolean("isFilm")) {
					multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(result.getInt("IDFilm"));
				} else {
					multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(result.getInt("IDTVSerie"));
				}
				advice = new Advice(user1, user2, multimedia);
				advices.add(advice);
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
		return advices;
	}

	@Override
	public void update(Advice friendship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Advice advice) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String delete;
			if (advice.getMultimedia() instanceof Film)
				delete = "delete FROM  \"Advice\" WHERE \"Adviser\" = ? AND \"Advisor\" = ? AND \"IDFilm\" = ?";
			else
				delete = "delete FROM  \"Advice\" WHERE \"Adviser\" = ? AND \"Advisor\" = ? AND \"IDTVSerie\" = ?";
				
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, advice.getAdvicer().getEmail());
			statement.setString(2, advice.getAdvisor().getEmail());
			statement.setInt(3, advice.getMultimedia().getId());
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

}

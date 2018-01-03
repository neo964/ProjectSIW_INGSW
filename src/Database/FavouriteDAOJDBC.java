package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Model.Favourite;
import Model.Multimedia;
import persistenceDAO.FavouriteDAO;

public class FavouriteDAOJDBC implements FavouriteDAO{

	private DataSource dataSource;
	
	public FavouriteDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Favourite favourite) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"Favourite\" (\"User\", \"IDMultimedia\", \"isFilm\", \"IDSerie\") values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, favourite.getUser());
			if (favourite.isFilm()) {
				statement.setInt(2, favourite.getMultimedia().getId());
				statement.setInt(4, -1);
			} else {
				statement.setInt(4, favourite.getMultimedia().getId());
				statement.setInt(2, -1);
			}
			statement.setBoolean(3, favourite.isFilm());
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
	public Favourite findByPrimaryKey(String user, int idmultimedia, boolean isFilm) {
		Connection connection = this.dataSource.getConnection();
		Favourite favourite = null;
		try {
			PreparedStatement statement;
			String query = "select * from \"Favourite\" where \"User\" = ? AND (\"IDMultimedia\" = ? OR \"IDSerie\" = ?)" ;
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			if (isFilm) {
				statement.setInt(2, idmultimedia);
				statement.setInt(3, -2);
			} else {
				statement.setInt(3, idmultimedia);
				statement.setInt(2, -2);
			}
			
			ResultSet result = statement.executeQuery();
			Multimedia multimedia = null;
			while (result.next()) {
				if (result.getBoolean("isFilm"))
					multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(result.getInt("IDMultimedia"));
				else
					multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(result.getInt("IDSerie"));
				favourite = new Favourite(result.getString("User"), multimedia, result.getBoolean("isFilm"));
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
		return favourite;
	}

	@Override
	public List<Favourite> findFavouriteUser(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Favourite> favourites = new LinkedList<>();
		try {
			Favourite favourite = null;
			PreparedStatement statement;
			String query = "select * from \"Favourite\" where \"User\" = ?" ;
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			Multimedia multimedia = null;
			while (result.next()) {
				if (result.getBoolean("isFilm"))
					multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(result.getInt("IDMultimedia"));
				else
					multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(result.getInt("IDSerie"));
				favourite = new Favourite(result.getString("User"), multimedia, result.getBoolean("isFilm"));
				favourites.add(favourite);
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
		return favourites;
	}

	@Override
	public List<Multimedia> findFavouriteUserMultimedia(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Multimedia> favourites = new LinkedList<>();
		try {
			PreparedStatement statement;
			String query = "select * from \"Favourite\" where \"User\" = ?" ;
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			Multimedia multimedia = null;
			while (result.next()) {
				if (result.getBoolean("isFilm"))
					multimedia = DatabaseManager.getInstance().getDaoFactory().getFilmDAO().findByPrimaryKey(result.getInt("IDMultimedia"));
				else
					multimedia = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO().findByPrimaryKey(result.getInt("IDSerie"));
				favourites.add(multimedia);
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
		return favourites;
	}

	@Override
	public void update(Favourite favourite) {
		/*Connection connection = this.dataSource.getConnection();
		try {
			String update = "update \"Favourite\" SET \"User\" = ?, \"IDMultimedia\" = ?, \"isFilm\" = ?, \"IDSerie\" = ? WHERE \"User\" = ? AND (\"IDMultimedia\" = ? OR \"IDSerie\" = ?)";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, favourite.getUser());
			statement.setInt(2, episode.getTVSerieID());
			statement.setInt(3, episode.getEpisode());
			statement.setInt(4, episode.getSeason());
			statement.setInt(5, episode.getTVSerieID());
			statement.setInt(6, episode.getEpisode());
			statement.setInt(7, episode.getSeason());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}*/
	}

	@Override
	public void delete(Favourite favourite) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM \"Favourite\" WHERE \"User\" = ? AND (\"IDMultimedia\" = ? OR \"IDSerie\" = ?)";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, favourite.getUser());
			if (favourite.isFilm()) {
				statement.setInt(2, favourite.getMultimedia().getId());
				statement.setInt(3, -1);
			} else {
				statement.setInt(3, favourite.getMultimedia().getId());
				statement.setInt(2, -1);
			}
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
			String delete = "delete FROM \"Favourite\" WHERE \"User\" = ?";
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

package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Actor;
import Model.Film;
import Model.FilmPoster;
import Model.Trailer;
import persistenceDAO.FilmDAO;

public class FilmDAOJDBC implements FilmDAO {

	private DataSource dataSource;
	private ActorInMultimediaDAOJDBC actors;
	
	public FilmDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
		actors = new ActorInMultimediaDAOJDBC(dataSource);
	}
	
	@Override
	public void save(Film film) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into \"Film\"(\"ID\", \"Title\", \"Category\", \"Year\", \"Director\", \"Trailer\", \"VideoOnDemand\", \"Plot\", \"Price\", \"Image\") values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, film.getId());
			statement.setString(2, film.getPoster().getTitle());
			statement.setString(3, film.getPoster().getCategory());
			statement.setInt(4, film.getPoster().getYear());
			statement.setString(5, film.getPoster().getDirector());
			statement.setString(6, film.getTrailer().getPath());
			statement.setString(7, film.getVideoOnDemand());
			statement.setString(8, film.getPoster().getPlot());
			statement.setDouble(9, film.getPrice());
			statement.setString(10, film.getPoster().getImage());
			statement.executeUpdate();
			for (String actor: film.getPoster().getActors()) {
				actors.save(new Actor(actor, film.getId(), true));
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
	}

	@Override
	public Film findByPrimaryKey(int codice) {
		Connection connection = this.dataSource.getConnection();
		Film film = null;
		try {
			List<String> actorsFilmString = actors.findAllNameActors(codice, true);
			PreparedStatement statement;
			String query = "select * from \"Film\" where \"ID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, codice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				film = new Film(result.getInt("ID"), new FilmPoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsFilmString, result.getString("Plot"), result.getString("Image")), new Trailer (result.getString("Trailer")), result.getDouble("Price") ,result.getString("VideoOnDemand"));
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
		return film;
	}

	@Override
	public List<Film> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Film> films = new java.util.LinkedList<Film>();
		try {
			Film film;
			PreparedStatement statement;
			String query = "select * from \"Film\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsFilmString = actors.findAllNameActors(result.getInt("ID"), true);
				film = new Film(result.getInt("ID"), new FilmPoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsFilmString, result.getString("Plot"), result.getString("Image")), new Trailer (result.getString("Trailer")), result.getDouble("Price") ,result.getString("VideoOnDemand"));
				films.add(film);
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
		return films;
	}
	
	@Override
	public List<Film> findByName(String name) {
		Connection connection = this.dataSource.getConnection();
		List<Film> films = new java.util.LinkedList<Film>();
		try {
			Film film;
			PreparedStatement statement;
			String query = "select * from \"Film\" where \"Title\" LIKE '%" + name + "%'";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsFilmString = actors.findAllNameActors(result.getInt("ID"), true);
				film = new Film(result.getInt("ID"), new FilmPoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsFilmString, result.getString("Plot"), result.getString("Image")), new Trailer (result.getString("Trailer")), result.getDouble("Price") ,result.getString("VideoOnDemand"));
				films.add(film);
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
		return films;
	}
	
	@Override
	public List<Film> findByCategory(String category) {
		Connection connection = this.dataSource.getConnection();
		List<Film> films = new java.util.LinkedList<Film>();
		try {
			Film film;
			PreparedStatement statement;
			String query = "select * from \"Film\" where \"Category\" LIKE '%" + category + "%'";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsFilmString = actors.findAllNameActors(result.getInt("ID"), true);
				film = new Film(result.getInt("ID"), new FilmPoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsFilmString, result.getString("Plot"), result.getString("Image")), new Trailer (result.getString("Trailer")), result.getDouble("Price") ,result.getString("VideoOnDemand"));
				films.add(film);
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
		return films;
	}

	@Override
	public List<Film> findByYear(int year) {
		Connection connection = this.dataSource.getConnection();
		List<Film> films = new java.util.LinkedList<Film>();
		try {
			Film film;
			PreparedStatement statement;
			String query = "select * from \"Film\" where \"Year\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, year);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsFilmString = actors.findAllNameActors(result.getInt("ID"), true);
				film = new Film(result.getInt("ID"), new FilmPoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsFilmString, result.getString("Plot"), result.getString("Image")), new Trailer (result.getString("Trailer")), result.getDouble("Price") ,result.getString("VideoOnDemand"));
				films.add(film);
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
		return films;
	}
	
	@Override
	public void update(Film film) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update \"Film\" SET \"ID\" = ?, \"Title\" = ?, \"Category\" = ?, \"Year\" = ?, \"Director\" = ?, \"Trailer\" = ?, \"VideoOnDemand\" = ?, \"Image\" = ? WHERE \"ID\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, film.getId());
			statement.setString(2, film.getPoster().getTitle());
			statement.setString(3, film.getPoster().getCategory());
			statement.setInt(4, film.getPoster().getYear());
			statement.setString(5, film.getPoster().getDirector());
			statement.setString(6, film.getTrailer().getPath());
			statement.setString(7, film.getVideoOnDemand());
			statement.setString(8, film.getPoster().getPlot());
			statement.setDouble(9, film.getPrice());
			statement.setString(10, film.getPoster().getImage());
			statement.setInt(11, film.getId());
			statement.executeUpdate();
			for (String actor : film.getPoster().getActors()) {
				actors.update(new Actor(actor, film.getId(), true));
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
	}

	@Override
	public void delete(Film film) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM \"Film\" WHERE \"ID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, film.getId());
			statement.executeUpdate();
			actors.deleteAllOfMultimedia(film.getId(), true);
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

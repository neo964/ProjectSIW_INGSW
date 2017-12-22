package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Actor;
import Model.TVSerie;
import Model.TVSeriePoster;
import Model.Trailer;
import persistenceDAO.TVSerieDAO;

public class TVSerieDAOJDBC implements TVSerieDAO {

	private DataSource dataSource;
	private ActorInMultimediaDAOJDBC actors;
	
	public TVSerieDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
		actors = new ActorInMultimediaDAOJDBC(dataSource);
	}
	
	@Override
	public void save(TVSerie tvSerie) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into TVSerie (ID, Title, Category, Director, Year, Completed, Season, Trailer, Plot, Price, Image) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, tvSerie.getId());
			statement.setString(2, tvSerie.getPoster().getTitle());
			statement.setString(3, tvSerie.getPoster().getCategory());
			statement.setString(4, tvSerie.getPoster().getDirector());
			statement.setInt(5, tvSerie.getPoster().getYear());
			statement.setBoolean(6, tvSerie.getTvPoster().isCompleted());
			statement.setInt(7, tvSerie.getTvPoster().getSeasons());
			statement.setString(8, tvSerie.getTrailer().getPath());
			statement.setString(9, tvSerie.getPoster().getPlot());
			statement.setDouble(10, tvSerie.getPrice());
			statement.setString(11, tvSerie.getPoster().getImage());
			statement.executeUpdate();
			for (String actor : tvSerie.getPoster().getActors()) {
				actors.save(new Actor(actor, tvSerie.getId(), false));
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
	public TVSerie findByPrimaryKey(int code) {
		Connection connection = this.dataSource.getConnection();
		TVSerie tvSerie = null;
		try {
			PreparedStatement statement;
			String query = "select * from TVSerie where ID = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(code, false);
				tvSerie = new TVSerie (new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Season")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
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
		return tvSerie;
	}

	@Override
	public List<TVSerie> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from TVSerie";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				tvserie = new TVSerie (new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Season")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				tvseries.add(tvserie);
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
		return tvseries;
	}
	
	public List<TVSerie> findByName (String name){
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from TVSeire where Title LIKE %?%";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				tvserie = new TVSerie (new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Season")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				tvseries.add(tvserie);
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
		return tvseries;
	}

	public List<TVSerie> findByCategory (String category){
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from TVSerie where Category LIKE %?%";
			statement = connection.prepareStatement(query);
			statement.setString(1, category);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				tvserie = new TVSerie (new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Season")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				tvseries.add(tvserie);
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
		return tvseries;
	}
	
	@Override
	public void update(TVSerie tvSerie) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update TVSerie SET ID = ?, Title = ?, Category = ?, Director = ?, Year = ?, Completed = ?, Seasons = ?, Trailer = ?, Plot = ?, Price = ?, Image = ? WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, tvSerie.getId());
			statement.setString(2, tvSerie.getPoster().getTitle());
			statement.setString(3, tvSerie.getPoster().getCategory());
			statement.setString(4, tvSerie.getPoster().getDirector());
			statement.setInt(5, tvSerie.getPoster().getYear());
			statement.setBoolean(6, tvSerie.getTvPoster().isCompleted());
			statement.setInt(7, tvSerie.getTvPoster().getSeasons());
			statement.setString(8, tvSerie.getTrailer().getPath());
			statement.setString(9, tvSerie.getPoster().getPlot());
			statement.setDouble(10, tvSerie.getPrice());
			statement.setString(11, tvSerie.getPoster().getImage());
			statement.setInt(12, tvSerie.getId());
			statement.executeUpdate();
			for (String actor : tvSerie.getPoster().getActors()) {
				actors.update(new Actor (actor, tvSerie.getId(), false));
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
	public void delete(TVSerie tvSerie) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM TVSerie WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, tvSerie.getId());
			statement.executeUpdate();
			actors.deleteAllOfMultimedia(tvSerie.getId(), false);
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

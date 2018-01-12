package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.Actor;
import Model.Episode;
import Model.Ranking;
import Model.TVSerie;
import Model.TVSeriePoster;
import Model.Trailer;
import persistenceDAO.TVSerieDAO;

public class TVSerieDAOJDBC implements TVSerieDAO {

	private DataSource dataSource;
	private ActorInMultimediaDAOJDBC actors;
	private EpisodeDAOJDBC episodes;
	
	public TVSerieDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
		actors = new ActorInMultimediaDAOJDBC(dataSource);
		episodes = new EpisodeDAOJDBC(dataSource);
	}
	
	private int findMax () {
		Connection connection = dataSource.getConnection();
		int i = -1;
		try {
			String query = "select MAX(\"ID\") as maxx from \"TVSerie\"";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				i = result.getInt("maxx");
				System.out.println(i);
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
		return i;
	}
	
	@Override
	public void save(TVSerie tvSerie) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"TVSerie\" (\"ID\", \"Title\", \"Category\", \"Director\", \"Year\", \"Completed\", \"Seasons\", \"Trailer\", \"Plot\", \"Price\", \"Image\") values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, findMax()+1);
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
			connection.commit();
			connection.setAutoCommit(true);
			for (String actor : tvSerie.getPoster().getActors()) {
				actors.save(new Actor(actor, tvSerie.getId(), false));
			}
			LinkedList<Episode>	allepisode = tvSerie.getAllEpisode();
			for (Episode episode : allepisode) {
				episodes.save(episode);
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
			String query = "select * from \"TVSerie\" where \"ID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(code, false);
				LinkedList<Episode> episodeTVSerie = (LinkedList<Episode>) episodes.findAll(code);
				tvSerie = new TVSerie (result.getInt("ID"), new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Seasons")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				for (int i = 0; i < result.getInt("Seasons"); i++) {
					tvSerie.addNewSeason();
				}
				for (Episode episode : episodeTVSerie) {
					tvSerie.addNewEpisode(episode, episode.getSeason());
				}
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
			TVSerie tvSerie;
			PreparedStatement statement;
			String query = "select * from \"TVSerie\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				LinkedList<Episode> episodeTVSerie = (LinkedList<Episode>) episodes.findAll(result.getInt("ID"));
				tvSerie = new TVSerie (result.getInt("ID"), new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Seasons")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				for (int i = 0; i < result.getInt("Seasons"); i++) {
					tvSerie.addNewSeason();
				}
				for (Episode episode : episodeTVSerie) {
					tvSerie.addNewEpisode(episode, episode.getSeason());
				}
				tvseries.add(tvSerie);
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
	
	@Override
	public List<TVSerie> findByName (String name){
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from \"TVSerie\" where \"Title\" LIKE '%" + name + "%'";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				LinkedList<Episode> episodeTVSerie = (LinkedList<Episode>) episodes.findAll(result.getInt("ID"));
				tvserie = new TVSerie (result.getInt("ID"), new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Seasons")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				for (int i = 0; i < result.getInt("Seasons"); i++) {
					tvserie.addNewSeason();
				}
				for (Episode episode : episodeTVSerie) {
					tvserie.addNewEpisode(episode, episode.getSeason());
				}
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
	public List<TVSerie> findByCategory (String category){
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from \"TVSerie\" where \"Category\" LIKE '%" + category + "%'";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				LinkedList<Episode> episodeTVSerie = (LinkedList<Episode>) episodes.findAll(result.getInt("ID"));
				tvserie = new TVSerie (result.getInt("ID"), new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Seasons")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				for (int i = 0; i < result.getInt("Seasons"); i++) {
					tvserie.addNewSeason();
				}
				for (Episode episode : episodeTVSerie) {
					tvserie.addNewEpisode(episode, episode.getSeason());
				}
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
	public List<TVSerie> findByYear (int year){
		Connection connection = this.dataSource.getConnection();
		List<TVSerie> tvseries = new java.util.LinkedList<>();
		try {
			TVSerie tvserie;
			PreparedStatement statement;
			String query = "select * from \"TVSerie\" where \"Year\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, year);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				List<String> actorsTVSerieString = actors.findAllNameActors(result.getInt("ID"), false);
				LinkedList<Episode> episodeTVSerie = (LinkedList<Episode>) episodes.findAll(result.getInt("ID"));
				tvserie = new TVSerie (result.getInt("ID"), new TVSeriePoster (result.getString("Title"), result.getString("Category"), result.getString("Director"), result.getInt("Year"), actorsTVSerieString, result.getString("Plot"), result.getString("Image"), result.getBoolean("Completed"), result.getInt("Seasons")), new Trailer (result.getString("Trailer")), result.getDouble("Price"));
				for (int i = 0; i < result.getInt("Seasons"); i++) {
					tvserie.addNewSeason();
				}
				for (Episode episode : episodeTVSerie) {
					tvserie.addNewEpisode(episode, episode.getSeason());
				}
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
			connection.setAutoCommit(false);
			String update = "update \"TVSerie\" SET \"ID\" = ?, \"Title\" = ?, \"Category\" = ?, \"Director\" = ?, \"Year\" = ?, \"Completed\" = ?, \"Seasons\" = ?, \"Trailer\" = ?, \"Plot\" = ?, \"Price\" = ?, \"Image\" = ? WHERE \"ID\" = ?";
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
			for (Episode episode : tvSerie.getAllEpisode()) {
				episodes.update(episode);
			}
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
	public void delete(TVSerie tvSerie) {
		Connection connection = this.dataSource.getConnection();
		try {
			RankingDAOJDBC rank = new RankingDAOJDBC(dataSource);
			rank.delete(new Ranking(tvSerie, null, 0));
			connection.setAutoCommit(false);
			String delete = "delete FROM \"TVSerie\" WHERE \"ID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, tvSerie.getId());
			statement.executeUpdate();
			actors.deleteAllOfMultimedia(tvSerie.getId(), false);
			episodes.deleteAllOfTVSerie(tvSerie.getId());;
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

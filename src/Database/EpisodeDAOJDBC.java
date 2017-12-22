package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.Episode;
import persistenceDAO.EpisodeDAO;

public class EpisodeDAOJDBC implements EpisodeDAO{

	private DataSource dataSource;
	
	public EpisodeDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Episode episode) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into Episode(Path, TVSerie, episode, season) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, episode.getPath());
			statement.setInt(2, episode.getTVSerieID());
			statement.setInt(3, episode.getEpisode());
			statement.setInt(4, episode.getSeason());
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
	public Episode findByPrimaryKey(int IDSerie, int season, int episode) {
		Connection connection = this.dataSource.getConnection();
		Episode realepisode = null;
		try {
			PreparedStatement statement;
			String query = "select * from Episode where TVSerie = ? AND episode = ? AND season = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, IDSerie);
			statement.setInt(2, episode);
			statement.setInt(3, season);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				realepisode = new Episode (result.getString("Path"), result.getInt("TVSerie"), result.getInt("season"), result.getInt("episode"));
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
		return realepisode;
	}

	@Override
	public List<Episode> findAll(int IDSerie) {
		Connection connection = this.dataSource.getConnection();
		List<Episode> episodes = new LinkedList<>();
		try {
			Episode episode = null;
			PreparedStatement statement;
			String query = "select * from Episode where TVSerie = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, IDSerie);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				episode = new Episode (result.getString("Path"), result.getInt("TVSerie"), result.getInt("season"), result.getInt("episode"));
				episodes.add(episode);
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
		return episodes;
	}

	@Override
	public void update(Episode episode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Episode SET Path = ?, TVSerie = ?, episode = ?, season = ? WHERE TVSerie = ?, episode = ?, season = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, episode.getPath());
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
		}
	}

	@Override
	public void delete(Episode episode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM Episode WHERE TVSerie = ?, episode = ?, season = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, episode.getTVSerieID());
			statement.setInt(2, episode.getEpisode());
			statement.setInt(3, episode.getSeason());
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

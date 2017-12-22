package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.Film;
import Model.Multimedia;
import Model.Ranking;
import persistenceDAO.RankingDAO;

public class RankingDAOJDBC implements RankingDAO {

	private DataSource dataSource;
	
	public RankingDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Ranking ranking) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = null;
			if (ranking.getMultimedia() instanceof Film)
				insert = "insert into RankingFilm (Rank, User, Film) values (?,?,?)";
			else
				insert = "insert into RankingTVSerie (Rank, User, TVSerie) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, ranking.getRank());
			statement.setString(2, ranking.getUser());
			statement.setInt(3, ranking.getMultimedia().getId());
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
	public Ranking findByPrimaryKey(String User, Multimedia multimedia) {
		Connection connection = this.dataSource.getConnection();
		Ranking ranking = null;
		try {
			PreparedStatement statement;
			String query = null;
			FilmDAOJDBC filmDAO = null;
			TVSerieDAOJDBC serieDAO = null;
			Multimedia multimediatmp;
			
			if (multimedia instanceof Film) {
				query = "select * from RankingFilm where User = ? AND Film = ?";
				filmDAO = new FilmDAOJDBC(dataSource);
			}
			else {
				query = "select * from RankingTVSerie where User = ? AND TVSerie = ?";
				serieDAO = new TVSerieDAOJDBC(dataSource);
			}
			statement = connection.prepareStatement(query);
			statement.setString(1, User);
			statement.setInt(2, multimedia.getId());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				if (multimedia instanceof Film) 
					multimediatmp = filmDAO.findByPrimaryKey(result.getInt("Film"));
				else 
					multimediatmp = serieDAO.findByPrimaryKey(result.getInt("TVSerie"));
				
				ranking = new Ranking(multimediatmp, result.getString("User"),result.getInt("Rank"));
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
		return ranking;
	}
	
	public float findRanking (Multimedia multimedia) {
		Connection connection = this.dataSource.getConnection();
		int number = 0;
		int voto = 0;
		try {
			PreparedStatement statement;
			String query = null;
			if (multimedia instanceof Film)
				query = "select * from RankingFilm where User = ? AND Film = ?";
			else
				query = "select * from RankingTVSerie where User = ? AND TVSerie = ?";
			
			statement = connection.prepareStatement(query);
			statement.setInt(3, multimedia.getId());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				number++;
				voto += result.getInt("Rank");
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
		return (float)voto/number;
	}

	@Override
	public List<Ranking> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Ranking> rankings = new LinkedList<>();
		try {
			Ranking ranking = null;
			PreparedStatement statement;
			String query = "select * from Ranking";
			FilmDAOJDBC filmDAO = new FilmDAOJDBC(dataSource);
			Multimedia multimediatmp = null;
			
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				multimediatmp =  filmDAO.findByPrimaryKey(result.getInt("Film"));
				ranking = new Ranking(multimediatmp, result.getString("User"),result.getInt("Rank"));
				rankings.add(ranking);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} 
		try {
			Ranking ranking = null;
			PreparedStatement statement;
			String query = "select * from RankingTVSerie";
			TVSerieDAOJDBC serieDAO = new TVSerieDAOJDBC(dataSource);
			Multimedia multimediatmp = null;
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				multimediatmp = serieDAO.findByPrimaryKey(result.getInt("TVSerie"));
				ranking = new Ranking(multimediatmp, result.getString("User"),result.getInt("Rank"));
				rankings.add(ranking);
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
		return rankings;
	}

	@Override
	public void update(Ranking ranking) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = null;
			if (ranking.getMultimedia() instanceof Film)
				update = "update RankingFilm SET Rank = ?, User = ?, Film = ? WHERE User = ?, Film = ?";
			else
				update = "update RankingTVSerie SET Rank = ?, User = ?, TVSerie = ? WHERE User = ?, TVSerie = ?";
				
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, ranking.getRank());
			statement.setString(2, ranking.getUser());
			statement.setInt(3, ranking.getMultimedia().getId());
			statement.setString(4, ranking.getUser());
			statement.setInt(5, ranking.getMultimedia().getId());
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
	public void delete(Ranking ranking) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = null;
			if (ranking.getMultimedia() instanceof Film)
				delete = "delete FROM RankingFilm WHERE User = ?, Film = ?";
			else
				delete = "delete FROM RankingTVSerie WHERE User = ?, TVSerie = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, ranking.getUser());
			statement.setInt(2, ranking.getMultimedia().getId());
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

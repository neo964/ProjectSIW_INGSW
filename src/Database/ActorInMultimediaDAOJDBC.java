package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Actor;
import persistenceDAO.ActorInMultimediaDAO;

public class ActorInMultimediaDAOJDBC implements ActorInMultimediaDAO{
private DataSource dataSource;
	
	public ActorInMultimediaDAOJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save (Actor actor) {
		Connection connection = dataSource.getConnection();
		try {
			String insert;
			if (actor.isFilm())
				insert = "insert into \"ActorInFilm\" (\"Actor\", \"FilmID\") values (?,?)";
			else
				insert = "insert into \"ActorInTVSerie\" (\"Actor\", \"TVSerieID\") values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, actor.getActor());
			statement.setInt(2, actor.getIdmultimedia());
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
	public Actor findByPrimaryKey(String name, int idmultimedia, boolean film) {
		Connection connection = this.dataSource.getConnection();
		Actor actor = null;
		try {
			PreparedStatement statement;
			String query;
			if (film)
				query = "select * from \"ActorInFilm\" where \"Actor\" = ? AND \"FilmID\" = ?";
			else
				query = "select * from \"ActorInTVSerie\" where \"Actor\" = ? AND \"TVSerieID\" = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, idmultimedia);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				if (film)
					actor = new Actor (result.getString("Actor"), result.getInt("FilmID"), film);
				else
					actor = new Actor (result.getString("Actor"), result.getInt("TVSerieID"), film);
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
		return actor;
	}

	@Override
	public List<Actor> findAll(int idmultimedia, boolean film) {
		Connection connection = this.dataSource.getConnection();
		List<Actor> actors = new java.util.LinkedList<>();
		try {
			Actor actor;
			PreparedStatement statement;
			String query;
			if (film)
				query = "select * from \"ActorInFilm\" where \"FilmID\" = ?";
			else
				query = "select * from \"ActorInTVSerie\" where \"TVSerieID\" = ?";
			
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (film)
					actor = new Actor (result.getString("Actor"), result.getInt("FilmID"), film);
				else
					actor = new Actor (result.getString("Actor"), result.getInt("TVSerieID"), film);
			
				actors.add(actor);
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
		return actors;
	}

	@Override
	public List<String> findAllNameActors(int idmultimedia, boolean film) {
		Connection connection = this.dataSource.getConnection();
		List<String> actors = new java.util.LinkedList<>();
		try {
			String actor;
			PreparedStatement statement;
			String query;
			if (film)
				query = "select * from \"ActorInFilm\" where \"FilmID\" = ?";
			else
				query = "select * from \"ActorInTVSerie\" where \"TVSerieID\" = ?";
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, idmultimedia);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (film)
					actor = new String (result.getString("Actor"));
				else
					actor = new String (result.getString("Actor"));
				actors.add(actor);
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
		return actors;
	}
	
	@Override
	public void update(Actor actor) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update;
			if (actor.isFilm())
				update = "update \"ActorInFilm\" SET \"Actor\" = ?, \"FilmID\" = ? WHERE \"Actor\" = ? AND \"FilmID\" = ?";
			else
				update = "update \"ActorInTVSerie\" SET \"Actor\" = ?, \"TVSerieID\" = ? WHERE \"Actor\" = ? AND \"TVSerieID\" = ?";
			
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, actor.getActor());
			statement.setInt(2, actor.getIdmultimedia());
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
	public void delete(Actor actor) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete;
			if (actor.isFilm())
				delete = "delete FROM \"ActorInFilm\" WHERE \"Actor\" = ? AND \"FilmID\" = ?";
			else
				delete = "delete FROM \"ActorInTVSerie\" WHERE \"Actor\" = ? AND \"TVSerieID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, actor.getActor());
			statement.setInt(2, actor.getIdmultimedia());
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
	public void deleteAllOfMultimedia(int idmultimedia, boolean film) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete;
			if (film)
				delete = "delete FROM \"ActorInFilm\" WHERE \"FilmID\" = ?";
			else
				delete = "delete FROM \"ActorInTVSerie\" WHERE \"TVSerieID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, idmultimedia);
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

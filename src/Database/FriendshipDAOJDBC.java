package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Model.Friendship;
import persistenceDAO.FriendshipDAO;

public class FriendshipDAOJDBC implements FriendshipDAO {

	private DataSource dataSource;
	
	public FriendshipDAOJDBC(DataSource data) {
		this.dataSource = data;
	}
	
	@Override
	public void save(Friendship friendship) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"Friendship\"(\"User1\", \"User2\", \"accepted\") values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
			statement.setBoolean(3, friendship.isAccepted ());
			statement.executeUpdate();
			statement.setString(2, friendship.getUser1());
			statement.setString(1, friendship.getUser2());
			statement.setBoolean(3, friendship.isAccepted ());
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
	public Friendship findByPrimaryKey(String friend1, String friend2) {
		Connection connection = this.dataSource.getConnection();
		Friendship friendship = null;
		try {
			PreparedStatement statement;
			String query = "select * from \"Friendship\" where \"User1\" = ? AND \"User2\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString (1, friend1);
			statement.setString (2, friend2);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				friendship = new Friendship(result.getString("User1"), result.getString("User2"), result.getBoolean("accepted"));
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
		return friendship;
	}

	@Override
	public List<Friendship> findAllMyFriend(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Friendship> friendships = new LinkedList<>();
		try {
			PreparedStatement statement;
			String query = "select * from \"Friendship\" where \"User1\" = ? And \"accepted\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString (1, user);
			statement.setBoolean(2, true);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				friendships.add(new Friendship (result.getString("User1"), result.getString("User2"), result.getBoolean("accepted")));
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
		return friendships;
	}
	
	@Override
	public List<Friendship> findAllMyRequest(String user) {
		Connection connection = this.dataSource.getConnection();
		List<Friendship> friendships = new LinkedList<>();
		try {
			PreparedStatement statement;
			String query = "select * from \"Friendship\" where \"User1\" = ? And \"accepted\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString (1, user);
			statement.setBoolean(2, false);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				friendships.add(new Friendship (result.getString("User1"), result.getString("User2"), result.getBoolean("accepted")));
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
		return friendships;
	}

	@Override
	public void update(Friendship friendship) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String update = "update \"Friendship\" SET \"User1\" = ?, \"User2\" = ?, \"accepted\" = ? WHERE \"User1\" = ? AND \"User2\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
			statement.setBoolean(3, friendship.isAccepted());
			statement.setString(4, friendship.getUser1());
			statement.setString(5, friendship.getUser2());
			statement.executeUpdate();
			statement.setString(2, friendship.getUser1());
			statement.setString(1, friendship.getUser2());
			statement.setBoolean(3, friendship.isAccepted());
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

	@Override
	public void delete(Friendship friendship) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String delete = "delete FROM  \"Friendship\" WHERE \"User1\" = ? AND \"User2\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
			statement.executeUpdate();
			statement.setString(2, friendship.getUser1());
			statement.setString(1, friendship.getUser2());
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

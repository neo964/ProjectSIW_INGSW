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
			String insert = "insert into \"Friendship\"(\"User1\", \"User2\") values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
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
			if (result.next()) {
				friendship = new Friendship(result.getString("User1"), result.getString("User2"));
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
			String query = "select * from \"Friendship\" where \"User1\" = ? OR \"User2\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString (1, user);
			statement.setString (2, user);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				friendships.add(new Friendship (result.getString("User1"), result.getString("User2")));
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
			String update = "update \"Friendship\" SET \"User1\" = ?, \"User2\" = ? WHERE \"User1\" = ? AND \"User2\" = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
			statement.setString(3, friendship.getUser1());
			statement.setString(4, friendship.getUser2());
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
	public void delete(Friendship friendship) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM  \"Friendship\" WHERE \"User1\" = ? AND \"User2\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, friendship.getUser1());
			statement.setString(2, friendship.getUser2());
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

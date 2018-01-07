package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Cart;
import Model.Film;
import Model.Multimedia;
import Model.MultimediaInCart;
import Model.User;
import persistenceDAO.CartDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.TVSerieDAO;
import persistenceDAO.UserDAO;

public class CartDAOJDBC implements CartDAO {

	private DataSource dataSource;
	
	public CartDAOJDBC(DataSource data) {
		this.dataSource = data;
	}
	
	@Override
	public void save(Cart cart) {
		Connection connection = dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String insert = "insert into \"Cart\" (\"User\", \"FilmID\", \"Quantity\", \"TVSerieID\") values (?,?,?,?)";
			List<MultimediaInCart> cartmp = cart.getCart();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, cart.getUser().getEmail());
			for (MultimediaInCart multimediaInCart : cartmp) {
				if (multimediaInCart.getMultimedia() instanceof Film) {
					statement.setInt(2, multimediaInCart.getMultimedia().getId());
					statement.setInt(4, -1);
				} else {
					statement.setInt(2, -1);
					statement.setInt(4, multimediaInCart.getMultimedia().getId());
				}
				statement.setInt(3, multimediaInCart.getQuantity());
				statement.executeUpdate();
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
	public Cart findByPrimaryKey(String name) {
		Connection connection = this.dataSource.getConnection();
		UserDAO userdao = DatabaseManager.getInstance().getDaoFactory().getUserDAO();
		User user = userdao.findByPrimaryKey(name);
		
		Cart cart = new Cart(user);
		try {
			PreparedStatement statement;
			String query = "select * from \"Cart\" where \"User\" = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Multimedia multimedia;
				if (result.getInt("FilmID") != -1) {
					FilmDAO filmdao = DatabaseManager.getInstance().getDaoFactory().getFilmDAO();
					multimedia = filmdao.findByPrimaryKey(result.getInt("FilmID"));
				} else {
					TVSerieDAO tvseriedao = DatabaseManager.getInstance().getDaoFactory().getTVSerieDAO();
					multimedia = tvseriedao.findByPrimaryKey(result.getInt("TVSerieID"));
				}
				cart.addToCart(new MultimediaInCart(multimedia, result.getInt("Quantity")));
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
		return cart;
	}

	@Override
	public void update(Cart cart) {
		/*Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Cart SET Actor = ?, TVSerieID = ? WHERE Actor = ? AND TVSerieID = ?";
			
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
		}*/
		return;
	}

	@Override
	public void delete(String name, int id, boolean film) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String delete;
			if (film)
				delete = "delete FROM \"Cart\" WHERE \"User\" = ? AND \"FilmID\" = ?";
			else
				delete = "delete FROM \"Cart\" WHERE \"User\" = ? AND \"TVSerieID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, name);
			statement.setInt(2, id);
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
	public void deleteAll(String user) {
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			String delete = "delete FROM \"Cart\" WHERE \"User\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, user);
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

package Database;

import Model.User;
import persistenceDAO.ActorInMultimediaDAO;
import persistenceDAO.AddressDAO;
import persistenceDAO.AdviceDAO;
import persistenceDAO.CartDAO;
import persistenceDAO.EpisodeDAO;
import persistenceDAO.FavouriteDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.FriendshipDAO;
import persistenceDAO.PaymentMethodDAO;
import persistenceDAO.RankingDAO;
import persistenceDAO.TVSerieDAO;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public class PostgresDAOFactory extends DAOFactory{

	private static DataSource data;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			data = new DataSource("jdbc:postgresql://baasu.db.elephantsql.com:5432/ijdowuke", "ijdowuke", "LrI8Xt-GSzhFGIH2SdDAYPnRnl1d0gWM");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	@Override
	public AddressDAO getAddressDAO() {
		return new AddressDAOJDBC(data);
	}

	@Override
	public EpisodeDAO getEpisodeDAO() {
		return new EpisodeDAOJDBC(data);
	}

	@Override
	public FilmDAO getFilmDAO() {
		return new FilmDAOJDBC(data);
	}

	@Override
	public PaymentMethodDAO getPaymentMethodDAO() {
		return new PaymentMethodDAOJDBC(data);
	}

	@Override
	public RankingDAO getRankingDAO() {
		return new RankingDAOJDBC(data);
	}

	@Override
	public TVSerieDAO getTVSerieDAO() {
		return new TVSerieDAOJDBC(data);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOJDBC(data);
	}

	@Override
	public UtilDAO getUtilDAO() {
		return new UtilDAO(data);
	}

	@Override
	public ActorInMultimediaDAO getActorInMultimedia() {
		return new ActorInMultimediaDAOJDBC(data);
	}

	@Override
	public CartDAO getCartDao() {
		return new CartDAOJDBC(data);
	}

	@Override
	public FriendshipDAO getFriendsip() {
		return new FriendshipDAOJDBC(data);
	}

	@Override
	public UserReference getUserRefernce(User user) {
		return new UserReference(data, user);
	}

	@Override
	public FavouriteDAO getFavouriteDAO() {
		return new FavouriteDAOJDBC (data);
	}

	@Override
	public AdviceDAO getAdviceDAO() {
		return new AdviceDAOJDBC(data);
	}
}

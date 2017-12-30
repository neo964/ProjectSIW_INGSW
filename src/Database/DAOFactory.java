package Database;

import Model.User;
import persistenceDAO.ActorInMultimediaDAO;
import persistenceDAO.AddressDAO;
import persistenceDAO.CartDAO;
import persistenceDAO.EpisodeDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.FriendshipDAO;
import persistenceDAO.PaymentMethodDAO;
import persistenceDAO.RankingDAO;
import persistenceDAO.TVSerieDAO;
import persistenceDAO.UserDAO;
import persistenceDAO.UserReference;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory (){
		return new PostgresDAOFactory ();
	}
	
	public abstract ActorInMultimediaDAO getActorInMultimedia ();
	
	public abstract AddressDAO getAddressDAO();
	
	public abstract CartDAO getCartDao ();
	
	public abstract EpisodeDAO getEpisodeDAO();
	
	public abstract FilmDAO getFilmDAO();
	
	public abstract FriendshipDAO getFriendsip ();
	
	public abstract PaymentMethodDAO getPaymentMethodDAO();
	
	public abstract RankingDAO getRankingDAO();
	
	public abstract TVSerieDAO getTVSerieDAO();

	public abstract UserDAO getUserDAO();
	
	public abstract UserReference getUserRefernce (User user);

	public abstract UtilDAO getUtilDAO();
}

package Database;

import persistenceDAO.AddressDAO;
import persistenceDAO.EpisodeDAO;
import persistenceDAO.FilmDAO;
import persistenceDAO.PaymentMethodDAO;
import persistenceDAO.RankingDAO;
import persistenceDAO.TVSerieDAO;
import persistenceDAO.UserDAO;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory (){
		return new PostgresDAOFactory ();
	}
	
	public abstract AddressDAO getAddressDAO();
	
	public abstract EpisodeDAO getEpisodeDAO();
	
	public abstract FilmDAO getFilmDAO();
	
	public abstract PaymentMethodDAO getPaymentMethodDAO();
	
	public abstract RankingDAO getRankingDAO();
	
	public abstract TVSerieDAO getTVSerieDAO();

	public abstract UserDAO getUserDAO();

	public abstract UtilDAO getUtilDAO();
}

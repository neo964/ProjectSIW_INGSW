package persistenceDAO;

import java.util.List;

import Model.Favourite;
import Model.Multimedia;

public interface FavouriteDAO {
	public void save(Favourite favourite);  // Create
	public Favourite findByPrimaryKey(String user, int idmultimedia, boolean isFilm);     // Retrieve
	public List<Favourite> findFavouriteUser(String user);       
	public List<Multimedia> findFavouriteUserMultimedia(String user);       
	public void update(Favourite favourite); //Update
	public void delete(Favourite favourite); //Delete
	public void deleteAllOfUser (String user);
}

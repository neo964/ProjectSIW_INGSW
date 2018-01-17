package persistenceDAO;

import java.util.List;

import Model.Film;

public interface FilmDAO {
	public void save(Film film);  // Create
	public Film findByPrimaryKey(int codice);     // Retrieve
	public List<Film> findAll();       
	public List<Film> findByName (String name);
	public List<Film> findByCategory (String category);
	public List<Film> findByYear (int year);
	public void update(Film film); //Update
	public void delete(Film film); //Delete	
}

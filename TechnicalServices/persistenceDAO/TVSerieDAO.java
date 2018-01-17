package persistenceDAO;

import java.util.List;

import Model.TVSerie;

public interface TVSerieDAO {
	public void save(TVSerie tvSerie);  // Create
	public TVSerie findByPrimaryKey(int code);     // Retrieve
	public List<TVSerie> findAll();       
	public List<TVSerie> findByName (String name);
	public List<TVSerie> findByCategory (String category);
	public List<TVSerie> findByYear (int year);
	public void update(TVSerie tvSerie); //Update
	public void delete(TVSerie tvSerie); //Delete

}

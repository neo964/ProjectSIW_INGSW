package persistenceDAO;

import java.util.List;

import Model.Episode;


public interface EpisodeDAO {
	public void save(Episode episode);  // Create
	public Episode findByPrimaryKey(int IDSerie, int season, int episode);     // Retrieve
	public List<Episode> findAll(int IDSerie);       
	public void update(Episode episode); //Update
	public void delete(Episode episode); //Delete
	public void deleteAllOfTVSerie (int code);
}
package persistenceDAO;

import java.util.List;

import Model.Multimedia;
import Model.Ranking;

public interface RankingDAO {
	public void save(Ranking ranking);  // Create
	public Ranking findByPrimaryKey(String User, Multimedia multimedia);     // Retrieve
	public List<Ranking> findAll();       
	public float findRanking (Multimedia multimedia);
	public void update(Ranking ranking); //Update
	public void delete(Ranking ranking); //Delete	
}

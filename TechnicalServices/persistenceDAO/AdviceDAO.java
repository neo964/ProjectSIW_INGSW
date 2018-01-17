package persistenceDAO;

import java.util.List;

import Model.Advice;

public interface AdviceDAO {
	public void save(Advice advice);  // Create
	public Advice findByPrimaryKey(String friend1, String friend2, int idmultimedia, boolean isFilm);     // Retrieve
	public List<Advice> findAllMyAdvice(String user);  
	public void update(Advice friendship); //Update
	public void delete(Advice friendship); //Delete
}

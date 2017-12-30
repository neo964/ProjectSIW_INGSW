package persistenceDAO;

import java.util.List;

import Model.Actor;

public interface ActorInMultimediaDAO {
	public void save(Actor actor);  // Create
	public Actor findByPrimaryKey(String name, int idmultimedia, boolean film);     // Retrieve
	public List<Actor> findAll(int idmultimedia, boolean film);     
	public List<String> findAllNameActors(int idmultimedia, boolean film);
	public void update(Actor actor); //Update
	public void delete(Actor actor); //Delete	
	public void deleteAllOfMultimedia(int idmultimedia, boolean film);
}

package persistenceDAO;

import java.util.List;

public interface Friendship {
	public void save(Friendship friendship);  // Create
	public Friendship findByPrimaryKey(String friend1, String friend2);     // Retrieve
	public List<Friendship> findAllMyFriend(String user);       
	public void update(Friendship friendship); //Update
	public void delete(Friendship friendship); //Delete

}

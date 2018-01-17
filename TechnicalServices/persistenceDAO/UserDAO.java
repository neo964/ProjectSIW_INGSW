package persistenceDAO;

import java.util.List;

import Model.User;

public interface UserDAO {
	public void save(User user);  // Create
	public User findByPrimaryKey(String name);     // Retrieve
	public List<User> findAll();       
	public List<User> findByName(String keyword);       
	public void update(User user); //Update
	public void delete(User user); //Delete	
	
	public void setPassword(User user, String password);
	public UserReference findByPrimaryKeyCredential(String name);     // Retrieve
}

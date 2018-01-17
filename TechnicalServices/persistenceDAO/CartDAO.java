package persistenceDAO;

import Model.Cart;

public interface CartDAO {
	public void save(Cart cart);  // Create
	public Cart findByPrimaryKey(String name);     // Retrieve     
	public void update(Cart cart); //Update
	public void delete(String name, int id, boolean film); //Delete
	public void deleteAll(String user); //Delete
}

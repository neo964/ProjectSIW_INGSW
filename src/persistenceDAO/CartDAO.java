package persistenceDAO;

import java.util.List;

import Model.Cart;
import Model.MultimediaInCart;

public interface CartDAO {
	public void save(Cart cart);  // Create
	public Cart findByPrimaryKey(String name, int id);     // Retrieve
	public List<MultimediaInCart> findYourCart (String name);       
	public void update(Cart cart); //Update
	public void delete(String name, int id); //Delete
	public void deleteAll(Cart cart); //Delete
}

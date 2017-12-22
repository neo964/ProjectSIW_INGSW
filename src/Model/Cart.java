package Model;

import java.util.LinkedList;
import java.util.List;

public class Cart {
	User user;
	List<MultimediaInCart> cart;
	
	public Cart (User user) {
		this.user = user;
		cart = new LinkedList<>();
	}
	
	public void addToCart (MultimediaInCart multimedia) {
		cart.add(multimedia);
	}
	
	public void removeToCart (MultimediaInCart multimedia) {
		cart.remove(multimedia);
	}
	
	public void removeAll () {
		cart.clear();
	}
	
}

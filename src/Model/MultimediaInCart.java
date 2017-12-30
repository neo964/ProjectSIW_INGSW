package Model;

public class MultimediaInCart {
	Multimedia multimedia;
	int quantity;
	
	public MultimediaInCart(Multimedia multimedia, int quantity) {
		this.multimedia = multimedia;
		this.quantity = quantity;
	}

	public Multimedia getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

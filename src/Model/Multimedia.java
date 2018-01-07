package Model;

public abstract class Multimedia {
	private int id;
	Poster poster;
	Trailer trailer;
	double price;
	
	public Multimedia() {}
	
	Multimedia (Poster poster, Trailer trailer, double price){
		this.poster = poster;
		this.trailer = trailer;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Poster getPoster() {
		return poster;
	}
	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}

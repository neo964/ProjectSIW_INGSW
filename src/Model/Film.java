package Model;

public class Film extends Multimedia{
	String videoOnDemand;
	double price;
	
	public Film() {}
	
	public Film(Poster poster, Trailer trailer, double price) {
		super(poster, trailer);
		this.price = price;
	}
	
	public Film (Film film) {
		super(film.poster, film.trailer);
		this.price = film.price;
		this.setId(film.getId());
	}
	
	public Film(int id, Poster poster, Trailer trailer, double price, String videoOnDemand) {
		super(poster, trailer);
		this.videoOnDemand = videoOnDemand;
		this.price = price;
		this.setId(id);
	}

	public String getVideoOnDemand() {
		return videoOnDemand;
	}

	public void setVideoOnDemand(String videoOnDemand) {
		this.videoOnDemand = videoOnDemand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

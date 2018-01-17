package Model;

public class Film extends Multimedia{
	String videoOnDemand;
	
	public Film() {}
	
	public Film(Poster poster, Trailer trailer, double price) {
		super(poster, trailer,price);
	}
	
	public Film (Film film) {
		super(film.poster, film.trailer, film.getPrice());
		this.setId(film.getId());
	}
	
	public Film(int id, Poster poster, Trailer trailer, double price, String videoOnDemand) {
		super(poster, trailer, price);
		this.videoOnDemand = videoOnDemand;
		this.setId(id);
	}

	public String getVideoOnDemand() {
		return videoOnDemand;
	}

	public void setVideoOnDemand(String videoOnDemand) {
		this.videoOnDemand = videoOnDemand;
	}

}

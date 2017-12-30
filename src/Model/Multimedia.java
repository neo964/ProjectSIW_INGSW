package Model;

public abstract class Multimedia {
	private int id;
	Poster poster;
	Trailer trailer;
	
	public Multimedia() {}
	
	Multimedia (Poster poster, Trailer trailer){
		this.poster = poster;
		this.trailer = trailer;
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
	
	
	
}

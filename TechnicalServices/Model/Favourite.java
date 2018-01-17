package Model;

public class Favourite {
	String user;
	Multimedia multimedia;
	boolean film;
	
	public Favourite() {}
	
	public Favourite (String user, Multimedia multimedia, boolean film){
		this.user = user;
		this.multimedia = multimedia;
		this.film = film;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Multimedia getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public boolean isFilm() {
		return film;
	}

	public void setFilm(boolean film) {
		this.film = film;
	}
	
	
}

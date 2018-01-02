package Model;

public class Actor {
	String actor;
	int idmultimedia;
	boolean film;
	
	public Actor () {}
	
	public Actor (String actor, int id, boolean film){
		this.actor = actor;
		this.idmultimedia = id;
		this.film = film;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public int getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(int idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	public boolean isFilm() {
		return film;
	}

	public void setFilm(boolean film) {
		this.film = film;
	}
	
	
}

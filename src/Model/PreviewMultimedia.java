package Model;

public class PreviewMultimedia {
	int id;
	String image;
	String title;
	boolean film;
	
	public PreviewMultimedia() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFilm() {
		return film;
	}

	public void setFilm(boolean film) {
		this.film = film;
	}

}

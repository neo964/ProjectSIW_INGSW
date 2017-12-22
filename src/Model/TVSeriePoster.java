package Model;

import java.util.List;

public class TVSeriePoster extends Poster{
	private boolean completed;
	private int seasons;
	
	public TVSeriePoster(String Title, String Category, String Director, int year, List<String> actors, String Plot, String Image, boolean completed, int seasons) {
		super(Title, Category, Director, year, actors, Plot, Image);
		this.completed = completed;
		this.seasons = seasons;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	
}

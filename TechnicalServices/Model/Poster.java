package Model;

import java.util.List;

public abstract class Poster {
	private String Title;
	private String Category;
	private String Director;
	private String Plot;
	private String Image;
	private int year;
	private List<String> Actors;
	
	public Poster(){}
	
	Poster (String Title, String Category, String Director, int year, List<String> actors, String Plot, String Image){
		this.Title = Title;
		this.Category = Category;
		this.Director = Director;
		this.year = year;
		this.Actors = actors;
		this.Plot = Plot;
		this.Image = Image;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getActors() {
		return Actors;
	}

	public void setActors(List<String> actors) {
		Actors = actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}
	
	
}

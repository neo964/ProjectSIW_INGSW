package Model;

import java.util.List;

public class FilmPoster extends Poster{

	public FilmPoster() {}
	
	public FilmPoster(String Title, String Category, String Director, int year, List<String> actors, String Plot, String Image) {
		super(Title, Category, Director, year, actors, Plot, Image);
	}

}

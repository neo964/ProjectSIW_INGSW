package Model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TVSerie extends Multimedia{
	List<LinkedList<Episode>> episodes;
	TVSeriePoster tvPoster;
	double price;
	
	public TVSerie(Poster poster, Trailer trailer, double price) {
		super(poster, trailer);
		tvPoster = (TVSeriePoster) poster;
		episodes = new LinkedList<LinkedList<Episode>>();
		this.price = price;
		
		for (int i = 0; i < this.tvPoster.getSeasons(); i++)
			episodes.add(new LinkedList<Episode>());
	}
	
	public void addNewSeason (){
		episodes.add(new LinkedList<Episode>());
	}
	
	public void addNewEpisode (Episode video, int nseason) {
		int i = 1;
		for (Iterator iterator = episodes.iterator(); iterator.hasNext(); i++) {
			LinkedList<Episode> season = (LinkedList<Episode>) iterator.next();
			if (i == nseason)
				season.add(video);
		}
	}

	public List<LinkedList<Episode>> getVideoOnDemand() {
		return episodes;
	}

	public void setVideoOnDemand(List<LinkedList<Episode>> videoOnDemand) {
		this.episodes = videoOnDemand;
	}

	public TVSeriePoster getTvPoster() {
		return tvPoster;
	}

	public void setTvPoster(TVSeriePoster tvPoster) {
		this.tvPoster = tvPoster;
	}

	public List<LinkedList<Episode>> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<LinkedList<Episode>> episodes) {
		this.episodes = episodes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

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
	
	public LinkedList<Episode> getSeason (int x){
		int i = 1;
		LinkedList<Episode> season = new LinkedList<>();
		for (Iterator<LinkedList<Episode>> iterator = episodes.iterator(); iterator.hasNext(); i++) {
			LinkedList<Episode> seasontmp = iterator.next();
			if (i == x) {
				for (Iterator<Episode> iteratorEp = seasontmp.iterator(); iterator.hasNext();) {
					Episode ep = iteratorEp.next();
					season.add(ep);
				}
				break;
			}
			i++;
		}
		return season;
	}
	
	public LinkedList<Episode> getAllEpisode (){
		LinkedList<Episode> allepisode = new LinkedList<>();
		for (Iterator<LinkedList<Episode>> iterator = episodes.iterator(); iterator.hasNext();) {
			LinkedList<Episode>	seasontmp = iterator.next();
				for (Iterator<Episode> iteratorEp = seasontmp.iterator(); iterator.hasNext();) {
						allepisode.add(iteratorEp.next());
			}
		}
		return allepisode;
	}
	
	public Episode getEpisode (int season, int episode) {
		int i = 1;
		Episode epi = null;
		for (Iterator<LinkedList<Episode>> iterator = episodes.iterator(); iterator.hasNext(); i++) {
			if (i == season) {
				LinkedList<Episode> seasontmp = iterator.next();
				int j = 1;
				for (Iterator<Episode> iteratorEp = seasontmp.iterator(); iterator.hasNext(); j++) {
					if (j == episode) {
						epi = iteratorEp.next();
						break;
					}
				}
				break;
			}
			i++;
		}
		return epi;
	}

	public List<LinkedList<Episode>> getAllSeasons() {
		return episodes;
	}

	public void setAllSeasons(List<LinkedList<Episode>> videoOnDemand) {
		this.episodes = videoOnDemand;
	}

	public TVSeriePoster getTvPoster() {
		return tvPoster;
	}

	public void setTvPoster(TVSeriePoster tvPoster) {
		this.tvPoster = tvPoster;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

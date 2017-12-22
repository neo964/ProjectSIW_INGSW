package Model;

public class Episode {
	private String Path;
	private int TVSerieID;
	private int season;
	private int episode;
	
	public Episode(String Path, int id, int season,int episode) {
		this.Path = Path;
		this.TVSerieID = id;
		this.season = season;
		this.episode = episode;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public int getTVSerieID() {
		return TVSerieID;
	}

	public void setTVSerieID(int tVSerieID) {
		TVSerieID = tVSerieID;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
	
	
}

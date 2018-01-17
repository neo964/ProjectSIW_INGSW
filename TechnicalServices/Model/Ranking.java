package Model;

public class Ranking {
	private Multimedia multimedia;
	private String user;
	private int rank;
	
	public Ranking(Multimedia multimedia, String user, int rank) {
		this.multimedia = multimedia;
		this.user = user;
		this.rank = rank;
	}

	public Multimedia getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}

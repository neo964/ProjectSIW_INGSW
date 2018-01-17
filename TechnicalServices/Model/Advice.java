package Model;

public class Advice {
	User advicer;
	User advisor;
	Multimedia multimedia;
	
	public Advice() {}
	
	public Advice (User ader, User ador, Multimedia multimedia){
		this.advicer = ader;
		this.advisor = ador;
		this.multimedia = multimedia;
	}

	public User getAdvicer() {
		return advicer;
	}

	public void setAdvicer(User advicer) {
		this.advicer = advicer;
	}

	public User getAdvisor() {
		return advisor;
	}

	public void setAdvisor(User advisor) {
		this.advisor = advisor;
	}

	public Multimedia getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}
	
}

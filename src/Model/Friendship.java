package Model;

public class Friendship {
	String user1;
	String user2;
	boolean accepted;
	
	public Friendship () {}
	
	public Friendship (String u1, String u2, boolean acc){
		user1 = u1;
		user2 = u2;
		accepted = acc;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}

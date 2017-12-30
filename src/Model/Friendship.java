package Model;

public class Friendship {
	String user1;
	String user2;
	
	public Friendship (String u1, String u2){
		user1 = u1;
		user2 = u2;
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
	
	
}

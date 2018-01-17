package Model;


public class UserSession {

	private String user;
	private String image;
	private String firstName;
	private String lastName;
	private boolean premium;
	private boolean admin;
	
	public UserSession() {};
	
	public UserSession(String user, boolean admin, boolean premium, String image, String firstname, String lastname) {
		this.user = user;
		this.admin = admin;
		this.premium = premium;
		this.image = image;
		this.firstName = firstname;
		this.lastName = lastname;
	}

	public String getUser() {
		return user;
	}

	public String getImage() {
		return image;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isPremium() {
		return premium;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
}

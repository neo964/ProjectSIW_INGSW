package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String FirstName;
	private String LastName;
	private String Password;
	private String email;
	private Address address;
	private PaymentMethod paymentMethod;
	private Date dateOfBirth;
	private boolean premium;
	private boolean admin;
	private String pathToImage;
	
	public User (String FirstName, String LastName, String email, Address address, PaymentMethod paymentMethod, boolean premium, boolean admin, Date birth, String pathToImage) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.email = email;
		this.address = address;
		this.paymentMethod = paymentMethod;
		this.premium = premium;
		this.admin = admin;
		this.dateOfBirth = birth;
		this.pathToImage = pathToImage;
	}
	
	public User (String FirstName, String LastName, String email, Address address, PaymentMethod paymentMethod, boolean premium, boolean admin, Date birth, String pathToImage, String Password) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.email = email;
		this.address = address;
		this.paymentMethod = paymentMethod;
		this.premium = premium;
		this.admin = admin;
		this.dateOfBirth = birth;
		this.pathToImage = pathToImage;
		this.Password = Password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String fisrtName) {
		FirstName = fisrtName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public int hashCode () {
		return this.email.hashCode();
	}
	
	@Override
	public boolean equals (Object user) {
		User tmpuser = (User) user;
		return this.getEmail().equals(tmpuser.getEmail());
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "User [" + this.getEmail() + ", " + 
					this.getAddress() + ", " +
						sdf.format(this.getDateOfBirth()) + ", " +
							this.getPaymentMethod() + "]";
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}
	
	
}

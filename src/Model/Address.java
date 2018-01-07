package Model;

public class Address {
	private String street;
	private String country;
	private String district;
	private String user;
	private int zipcode;
	
	public Address() {}
	
	public Address(String street, String country, String district, String user, int zipcode) {
		this.street = street;
		this.country = country;
		this.district = district;
		this.user = user;
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

}

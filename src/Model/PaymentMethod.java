package Model;

import java.util.Date;

public class PaymentMethod {
	private String cardNumber;
	private String user;
	private int code;
	private Date expirationDate;
	
	public PaymentMethod() {}
	
	public PaymentMethod(String CardNumber, String user, int code, Date expirationDate) {
		this.cardNumber = CardNumber;
		this.user = user;
		this.code = code;
		this.expirationDate = expirationDate;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
}

package users;

public class RegistredUser extends AbstractUser {
	private String login;
	private String password;
	private String phoneNumber;
	private String email;
	private boolean admin;
	
	
	public String getLogin() {
		return login;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String mail) throws IllegalArgumentException{
		if(!mail.matches("\\w+@\\w+\\.\\w{2,7}")) {
			throw new IllegalArgumentException();
		}
		this.email = mail;
	}
	
	public void setPhoneNumber(String number) throws IllegalArgumentException {
		if(!number.matches("(80|\\+375)-?\\d{2}[\\s-]?(\\d{1,3}[\\s-]?)+")) {
			throw new IllegalArgumentException();
		}
		this.phoneNumber = number;
	}
}

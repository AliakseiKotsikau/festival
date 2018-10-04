
public class MainClass {

	public static void main(String[] args) {
		Event event = new Event (100, "11.11.2018","Concert" ))
		Festival fest = new Festival(event);
		
		try {
			for(int i =0; i<110; i++) {
				fest.addUser(new RegistredUser(), event);
			}
		}catch (WrongNumberOfPeopleException e) {
			e.getMessage();
		}
		

	}

}

package events;

public class Festival {
	private EventInfo info;
	private ArrayList users;
	
	public void addUser(RegistredUser user) {
		if(users.size() == info.getMaxPeople())
			throw new WrongNumberOfPeopleException(); 
	}
	
}

class WrongNumberOfPeopleException extends Exception {
	
}

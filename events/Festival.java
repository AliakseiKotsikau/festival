package events;

public class Festival {
	private EventInfo info;
	private ArrayList<RegistredUser> users;
	
	public void addUser(RegistredUser user) throws WrongNumberOfPeopleException {
		if(users.size() == info.getMaxPeople())
			throw new WrongNumberOfPeopleException("No more people can be registred"); 
	}
	
    
	
}

class WrongNumberOfPeopleException extends Exception {
	public WrongNumberOfPeopleException(String message) {
		super(message);
	}
	
}

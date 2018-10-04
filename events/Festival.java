package events;

public class Festival {
	private ArrayList<EventInfo> events;
	private ArrayList<RegistredUser> users;
	
	public Festival(EventInfo info) {
		this.info = info;
	}
	
	public void addUser(RegistredUser user, EventInfo info) throws WrongNumberOfPeopleException {
		if(users.size() == info.getMaxPeople())
			throw new WrongNumberOfPeopleException("No more people can be registred"); 
	}
	
    
	
}

class WrongNumberOfPeopleException extends Exception {
	public WrongNumberOfPeopleException(String message) {
		super(message);
	}
	
}

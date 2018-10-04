package events;

public class Festival {
	private ArrayList<Event> events;
	
	public Festival(Event event) {
		this();
		events.add(event);
	}
	
	public Festival() {
		events = new ArrayList<>();
	}
	
	
	public void addUser(RegistredUser user, Event event) throws WrongNumberOfPeopleException {
		if(getUsers.size() == event.getMaxPeople())
			throw new WrongNumberOfPeopleException("No more people can be registred"); 
	}
	
	public ArrayList<RegistredUser> getUsers(Event event){
		return event.getUsers();
	}
	
    
	
}

class WrongNumberOfPeopleException extends IOException {
	public WrongNumberOfPeopleException(String message) {
		super(message);
	}
	
}

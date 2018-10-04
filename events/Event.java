package events;

import java.util.ArrayList;

import users.RegistratedUser;

public abstract class Event {
	private EventInfo info;
	private ArrayList<RegistratedUser> users;
	
	public Event(int maxPeople, String date, String place) {
		info = new EventInfo(maxPeople, date, place);
		users = new ArrayList<>(maxPeople);
	}
	
	public ArrayList<RegistratedUser> getUsers() {
		return users;
	}
	

}

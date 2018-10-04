package events;

import java.util.Date;

public class EventInfo  {
    private int maxPeople;
    private Date date;
    private String place;
    
    public EventInfo(int maxPeople, Date date, String place) {
    	this.maxPeople = maxPeople;
    	this.date = date;
    	this.place = place;
    }
    
    public int getMaxPeople() {
    	return maxPeople;
    }
    
    public Date getDate( ) {
    	return date;
    }
    
    public String getPlace( ) {
    	return place;
    }
}

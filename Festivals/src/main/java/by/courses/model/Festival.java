package by.courses.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Festival")
public class Festival {
	private Long id;
	private String name;
	private Date date;
	private String place;
	private Integer seating;

	private Set<Participant> participants = new HashSet<>();

	@JsonIgnore
	private Set<Performer> performers = new HashSet<>();

	// Add participants and performers

	public void addParticipant(Participant part) {
		if (part == null)
			throw new NullPointerException();
		participants.add(part);
	}

	public void addPerformer(Performer perf) {
		if (perf == null)
			throw new NullPointerException();
		performers.add(perf);
	}

	// Getters and setters

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "festival_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "place")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "seating")
	public Integer getSeating() {
		return seating;
	}

	public void setSeating(Integer seating) {
		this.seating = seating;
	}

	@ManyToMany(mappedBy = "festivals")
	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	@ManyToMany(mappedBy = "festivals_perf")
	public Set<Performer> getPerformers() {
		return performers;
	}

	public void setPerformers(Set<Performer> performers) {
		this.performers = performers;
	}

	// equals and hashcode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Festival other = (Festival) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Festival [name=" + name + ", date=" + date + ", place=" + place + ", seating=" + seating + "]";
	}

}

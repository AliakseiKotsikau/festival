package by.courses.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Participant")
public class Participant {
	private Long participant_id;
	private Long user_id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Integer age;

	private Set<Festival> festivals = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id")
	public Long getParticipant_id() {
		return participant_id;
	}

	public void addFest(Festival fest) {
		if (fest == null)
			throw new NullPointerException();
		festivals.add(fest);
	}

	// Getters and setters

	public void setParticipant_id(Long participant_id) {
		this.participant_id = participant_id;
	}

	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (!phone.matches("(80|\\+375)-?\\d{2}[\\s-]?(\\d{1,3}[\\s-]?)+")) {
			throw new IllegalArgumentException();
		}
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!email.matches("\\w+@\\w+\\.\\w{2,7}")) {
			throw new IllegalArgumentException();
		}
		this.email = email;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Festival_Participant", joinColumns = { @JoinColumn(name = "participant_id") }, inverseJoinColumns = { @JoinColumn(name = "festival_id") })
	public Set<Festival> getFestivals() {
		return festivals;
	}

	public void setFestivals(Set<Festival> festivals) {
		this.festivals = festivals;
	}

	@Column(name = "user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	// equals and hashcode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant_id == null) ? 0 : participant_id.hashCode());
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
		Participant other = (Participant) obj;
		if (participant_id == null) {
			if (other.participant_id != null)
				return false;
		} else if (!participant_id.equals(other.participant_id))
			return false;
		return true;
	}

}

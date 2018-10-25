package by.proj.Festival.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.proj.Festival.events.Festival;

@Entity
@Table(name = "Participant")
public class Participant {
	private Long participant_id;
	private String firstName;
	private String lastNmae;
	private String phone;
	private String email;
	private Integer age;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Login login;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Festival_Participant", joinColumns = { @JoinColumn(name = "participant_id") }, inverseJoinColumns = { @JoinColumn(name = "festival_id") })
	private Set<Festival> festivals = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_id")
	public Long getParticipant_id() {
		return participant_id;
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
	public String getLastNmae() {
		return lastNmae;
	}

	public void setLastNmae(String lastNmae) {
		this.lastNmae = lastNmae;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

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

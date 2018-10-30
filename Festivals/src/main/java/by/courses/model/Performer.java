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
@Table(name = "Performer")
public class Performer {
	private Long performer_id;
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "festival_performer", joinColumns = { @JoinColumn(name = "performer_id") }, inverseJoinColumns = { @JoinColumn(name = "festival_id") })
	private Set<Festival> festivals_perf = new HashSet<>();

	public void addFest(Festival fest) {
		if (fest == null)
			throw new NullPointerException();
		festivals_perf.add(fest);
	}

	// Getters and setters

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "performer_id")
	public Long getPerformer_id() {
		return performer_id;
	}

	public void setPerformer_id(Long performer_id) {
		this.performer_id = performer_id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "festival_performer", joinColumns = { @JoinColumn(name = "performer_id") }, inverseJoinColumns = { @JoinColumn(name = "festival_id") })
	public Set<Festival> getFestivals_perf() {
		return festivals_perf;
	}

	public void setFestivals_perf(Set<Festival> festivals_perf) {
		this.festivals_perf = festivals_perf;
	}

	// equals and hashcode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((performer_id == null) ? 0 : performer_id.hashCode());
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
		Performer other = (Performer) obj;
		if (performer_id == null) {
			if (other.performer_id != null)
				return false;
		} else if (!performer_id.equals(other.performer_id))
			return false;
		return true;
	}

}

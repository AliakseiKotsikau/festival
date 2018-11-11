package by.courses.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.courses.model.Participant;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

	@Query("select p from Participant p where p.user_id = ?1")
	Participant findByUser_id(Long user_id);

	Participant findByEmail(String email);
}

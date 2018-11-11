package by.courses.repositories;

import org.springframework.data.repository.CrudRepository;

import by.courses.model.Performer;

public interface PerformerRepository extends CrudRepository<Performer, Long> {

}

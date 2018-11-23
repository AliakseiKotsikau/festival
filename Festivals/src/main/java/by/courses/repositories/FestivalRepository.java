package by.courses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.courses.model.Festival;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long> {

	Festival findByName(String name);

}

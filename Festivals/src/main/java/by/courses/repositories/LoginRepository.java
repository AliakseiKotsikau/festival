package by.courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.courses.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsername(String username);
}

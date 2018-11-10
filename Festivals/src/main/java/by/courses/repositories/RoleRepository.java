package by.courses.repositories;

import org.springframework.data.repository.CrudRepository;

import by.courses.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);

}

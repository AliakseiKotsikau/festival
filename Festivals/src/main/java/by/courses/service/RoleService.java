package by.courses.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import by.courses.model.Role;
import by.courses.model.RoleInfo;
import by.courses.repositories.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public Set<RoleInfo> setActions() {
		HashSet<RoleInfo> actions = new HashSet<>();
		Role admin = roleRepository.findByRole("ROLE_ADMIN");

		return actions;
	}

}

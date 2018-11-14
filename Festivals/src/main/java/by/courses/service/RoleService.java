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
		RoleInfo adminInfo = new RoleInfo();
		adminInfo.setRole(admin);
		adminInfo.getActions().add("signup");
		adminInfo.getActions().add("change");
		adminInfo.getActions().add("addfest");
		adminInfo.getActions().add("addperf");
		adminInfo.getActions().add("admin");
		actions.add(adminInfo);

		Role user = roleRepository.findByRole("ROLE_USER");
		RoleInfo userInfo = new RoleInfo();
		userInfo.setRole(user);
		userInfo.getActions().add("signup");
		actions.add(userInfo);

		return actions;
	}

}

package by.courses.controllers.rest;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.courses.model.Festival;
import by.courses.model.Role;
import by.courses.model.RoleInfo;
import by.courses.service.FestivalService;
import by.courses.service.LoginService;
import by.courses.service.RoleService;

@RestController
@RequestMapping("/data")
@Profile("rest")
public class FestivalRest {

	private FestivalService service;
	private RoleService roleService;
	private LoginService loginService;

	public FestivalRest(FestivalService service, RoleService roleService, LoginService loginService) {
		super();
		this.service = service;
		this.roleService = roleService;
		this.loginService = loginService;
	}

	// list of festivals
	@RequestMapping("/festivals")
	public Iterable<Festival> getFestivalsData() {
		return service.getFestivals();
	}

	@RequestMapping("/festivals/{id}")
	public Festival getFest(@PathVariable() String id) {
		return service.getFestival(id);
	}

	// role's permissions
	@RequestMapping("/rolesinfo")
	public Set<RoleInfo> getActions() {
		return roleService.setActions();
	}

	@RequestMapping("/userroles")
	public Set<Role> getCurrentUser() {
		return loginService.getCurentUserRoles();
	}

}
package by.courses.controllers.rest;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.courses.model.Festival;
import by.courses.model.RoleInfo;
import by.courses.service.FestivalService;
import by.courses.service.RoleService;

@RestController
@RequestMapping("/data")
@Profile("rest")
public class FestivalRest {

	private FestivalService service;
	private RoleService roleService;

	public FestivalRest(FestivalService service, RoleService roleService) {
		super();
		this.service = service;
		this.roleService = roleService;
	}

	@RequestMapping("/festivals")
	public Iterable<Festival> getFestivalsData() {
		return service.getFestivals();
	}

	@RequestMapping("/festivals/{id}")
	public Festival getFest(@PathVariable() String id) {
		return service.getFestival(id);
	}

	@RequestMapping("/roles")
	public Set<RoleInfo> getActions() {
		return roleService.setActions();
	}

}
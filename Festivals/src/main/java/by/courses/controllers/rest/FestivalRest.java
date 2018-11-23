package by.courses.controllers.rest;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.courses.model.Festival;
import by.courses.model.Performer;
import by.courses.model.Role;
import by.courses.model.RoleInfo;
import by.courses.service.FestivalService;
import by.courses.service.LoginService;
import by.courses.service.PerformerService;
import by.courses.service.RoleService;

@RestController
@RequestMapping("/data")
@Profile("rest")
public class FestivalRest {

	private FestivalService service;
	private RoleService roleService;
	private LoginService loginService;
	private PerformerService performerService;

	public FestivalRest(FestivalService service, RoleService roleService, LoginService loginService, PerformerService performerService) {
		super();
		this.service = service;
		this.roleService = roleService;
		this.loginService = loginService;
		this.performerService = performerService;
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

	// roles of user
	@RequestMapping("/userroles")
	public Set<Role> getCurrentUser() {
		return loginService.getCurentUserRoles();
	}

	@RequestMapping("/festivals/{id}/perfs")
	public Set<Performer> getPerformersOfFest(@PathVariable() String id) {
		return service.getFestival(id).getPerformers();
	}

	@RequestMapping("/perfs")
	public Iterable<Performer> getAllPerformers() {
		return performerService.getPerformers();
	}

	@RequestMapping(value = "/festivals/{id}/addperf", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Performer> saveNewPerformer(@RequestBody Performer perf, @PathVariable() String id) {
		if (perf != null) {
			service.saveNewPerformer(id, perf);
		}

		return new ResponseEntity<Performer>(perf, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivals/addfest", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Festival> saveNewFestival(@RequestBody Festival fest) {
		if (fest != null) {
			service.saveNewFestival(fest);
		}

		return new ResponseEntity<Festival>(fest, HttpStatus.OK);
	}

	@RequestMapping(value = "/festivals/{id}/change", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Festival> changeFestival(@RequestBody Festival fest, @PathVariable() String id) {
		System.out.println(fest);
		if (fest != null) {
			service.changeFestival(fest);
		}

		return new ResponseEntity<Festival>(fest, HttpStatus.OK);
	}

}
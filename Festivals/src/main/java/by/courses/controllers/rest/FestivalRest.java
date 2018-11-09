package by.courses.controllers.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.courses.model.Festival;
import by.courses.service.FestivalService;

@RestController
@RequestMapping("/festivals")
@Profile("rest")
public class FestivalRest {

	private FestivalService service;

	public FestivalRest(FestivalService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/get")
	public Iterable<Festival> getFestivalsData() {
		return service.getFestivals();
	}

}
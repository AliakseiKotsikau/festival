package by.courses.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.courses.model.Festival;
import by.courses.service.FestivalService;

@RestController
public class FestivalControllerRest {

	private FestivalService service;

	public FestivalControllerRest(FestivalService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/festivals/get")
	public Iterable<Festival> getFestivals() {
		return service.getFestivals();
	}
}

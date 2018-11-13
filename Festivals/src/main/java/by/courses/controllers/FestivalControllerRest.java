package by.courses.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.courses.service.FestivalService;

@Controller
@RequestMapping("/festivals")
@Profile("rest")
public class FestivalControllerRest {

	private FestivalService service;

	public FestivalControllerRest(FestivalService service) {
		super();
		this.service = service;
	}

	@RequestMapping({ "", "/" })
	public String getFestivals() {
		return "rest/festivalsRest2";
	}

	@RequestMapping(value = "/{id}")
	public String getPerformers(@PathVariable() String id, Model model) {
		return "rest/single-fest-Rest";
	}

}

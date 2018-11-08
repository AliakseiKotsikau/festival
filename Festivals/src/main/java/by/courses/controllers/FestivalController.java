package by.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.courses.model.Festival;
import by.courses.service.FestivalService;

@Controller
@RequestMapping("/festivals")
public class FestivalController {

	private FestivalService service;

	public FestivalController(FestivalService service) {
		super();
		this.service = service;
	}

	@RequestMapping({ "", "/" })
	public String getFestivals() {
		return "festivals";
	}

	@RequestMapping(value = "/{id}")
	public String getPerformers(@PathVariable() String id, Model model) {
		Festival fest = service.getFestival(id);
		model.addAttribute("festival", fest);
		model.addAttribute("performers", fest.getPerformers());
		return "single-fest";
	}

	@RequestMapping(value = "/{id}/signup")
	public String addParticipant(@PathVariable String id) {
		service.addParticpipant(id);
		return "festivals";
	}

}

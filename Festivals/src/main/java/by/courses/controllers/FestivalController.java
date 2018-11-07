package by.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.courses.model.Festival;
import by.courses.repositories.FestivalRepository;

@Controller
@RequestMapping("/festivals")
public class FestivalController {

	@Autowired
	private FestivalRepository repository;

	public FestivalController(FestivalRepository repository) {
		super();
		this.repository = repository;
	}

	@RequestMapping({"","/"})
	public String getFestivals(Model model) {
		model.addAttribute("festivals", repository.findAll());
		return "festivals";
	}
	
	@RequestMapping(value="/{id}")
	public String getPerformers(@PathVariable() String id, Model model) {
		Festival fest = repository.findById(Long.parseLong(id)).get();
		model.addAttribute("festival", fest);
		model.addAttribute("performers", fest.getPerformers());
	
		return "single-fest";
	}
	
	

}

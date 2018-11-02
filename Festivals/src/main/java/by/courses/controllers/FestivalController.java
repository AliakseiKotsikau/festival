package by.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import by.courses.repositories.FestivalRepository;

@Controller
public class FestivalController {

	@Autowired
	private FestivalRepository repository;

	public FestivalController(FestivalRepository repository) {
		super();
		this.repository = repository;
	}

//	@RequestMapping("/festivals")
//	public @ResponseBody Iterable<Festival> getFestivals() {
//		return repository.findAll();
//	}

	@RequestMapping("/festivals")
	public String getFestivals(Model model) {
		model.addAttribute("festivals", repository.findAll());
		return "festivals";
	}

}

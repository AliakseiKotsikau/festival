package by.courses.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.courses.model.Festival;
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

	@RequestMapping(value = "/addfest", method = RequestMethod.GET)
	public String addFestival(Model model) {
		Festival fest = new Festival();
		model.addAttribute("festival", fest);
		return "festivals/addFestival";
	}

	@RequestMapping(value = "/{id}/signup")
	public String addParticipant(@PathVariable String id, Model model) {
		try {
			service.addParticpipant(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}
		return "redirect:/festivals";
	}

	@RequestMapping(value = "/{festid}/deleteperf/{perfid}")
	public String removePerformer(@PathVariable String festid, @PathVariable String perfid) {
		service.deletePerformer(festid, perfid);
		return "redirect:/festivals";
	}

}

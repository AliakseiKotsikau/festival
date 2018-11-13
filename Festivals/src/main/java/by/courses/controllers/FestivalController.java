package by.courses.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.courses.model.Festival;
import by.courses.model.Performer;
import by.courses.service.FestivalService;

@Controller
@RequestMapping("/festivals")
@Profile("thyme")
public class FestivalController {

	private FestivalService service;

	public FestivalController(FestivalService service) {
		super();
		this.service = service;
	}

	@RequestMapping({ "", "/" })
	public String getFestivals(Model model) {
		model.addAttribute("festivals", service.getFestivals());
		return "festivals/festivals";
	}

	@RequestMapping(value = "/{id}")
	public String getPerformers(@PathVariable() String id, Model model) {
		Festival fest = service.getFestival(id);
		model.addAttribute("festival", fest);
		model.addAttribute("performers", fest.getPerformers());
		return "festivals/single-fest";
	}

	@RequestMapping(value = "/{id}/signup")
	public String addParticipant(@PathVariable String id, Model model) {
		service.addParticpipant(id);
		model.addAttribute("festivals", service.getFestivals());
		return "festivals/festivals";
	}

	// Adding performers

	@RequestMapping(value = "/{id}/addperf", method = RequestMethod.GET)
	public String addPerformer(Model model) {
		Performer perf = new Performer();
		model.addAttribute("performer", perf);
		return "festivals/addPerformer";
	}

	@RequestMapping(value = "/{id}/addperf", method = RequestMethod.POST)
	public String savePerformer(@PathVariable String id, @ModelAttribute("performer") Performer performer, Model model, final RedirectAttributes redirectAttributes) {

		try {
			service.saveNewPerformer(id, performer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "festivals/addPerformer";
		}

		Festival fest = service.getFestival(id);
		redirectAttributes.addFlashAttribute("festival", fest);
		redirectAttributes.addFlashAttribute("performers", fest.getPerformers());
		return "redirect:/festivals/{id}";
	}

	// Adding festivals

	@RequestMapping(value = "/addfest", method = RequestMethod.GET)
	public String addFestival(Model model) {
		Festival fest = new Festival();
		model.addAttribute("festival", fest);
		return "festivals/addFestival";
	}

	@RequestMapping(value = "/addfest", method = RequestMethod.POST)
	public String saveFestival(Model model, @ModelAttribute("festival") Festival festival, final RedirectAttributes redirectAttributes) {

		try {
			service.saveNewFestival(festival);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "addFestival";
		}

		redirectAttributes.addFlashAttribute("festivals", service.getFestivals());

		return "redirect:/festivals";
	}

	// Change festival info

	@RequestMapping(value = "/{id}/change", method = RequestMethod.GET)
	public String changeFestival(@PathVariable String id, Model model) {
		Festival fest = service.getFestival(id);
		model.addAttribute("festival", fest);

		return "festivals/changeFestival";
	}

	@RequestMapping(value = "/{id}/change", method = RequestMethod.POST)
	public String saveChanges(@PathVariable String id, Model model, @ModelAttribute("festival") Festival festival, final RedirectAttributes redirectAttributes) {
		try {
			service.saveNewFestival(festival);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "festivals/changeFestival";
		}

		redirectAttributes.addFlashAttribute("festivals", service.getFestivals());

		return "redirect:/festivals";

	}

}

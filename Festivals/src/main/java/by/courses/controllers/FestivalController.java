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
import by.courses.service.PerformerService;

@Controller
@RequestMapping("/festivals")
@Profile("thyme")
public class FestivalController {

	private FestivalService service;
	private PerformerService performerService;

	public FestivalController(FestivalService service, PerformerService performerService) {
		super();
		this.service = service;
		this.performerService = performerService;
	}

	@RequestMapping({ "", "/" })
	public String getFestivals(Model model) {
		model.addAttribute("festivals", service.getFestivals());
		return "festivals/festivals";
	}

	/*
	 * Info about festival
	 * 
	 */
	@RequestMapping(value = "/{id}")
	public String getPerformers(@PathVariable() String id, Model model) {
		Festival fest = service.getFestival(id);
		model.addAttribute("festival", fest);
		model.addAttribute("performers", fest.getPerformers());
		return "festivals/single-fest";
	}

	/*
	 * Signing on festival
	 * 
	 */
	@RequestMapping(value = "/{id}/signup")
	public String addParticipant(@PathVariable String id, Model model) {
		try {
			service.addParticpipant(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			Festival fest = service.getFestival(id);
			model.addAttribute("festival", fest);
			model.addAttribute("performers", fest.getPerformers());
			return "festivals/single-fest";
		}
		model.addAttribute("festivals", service.getFestivals());
		return "festivals/festivals";
	}

	/*
	 * 
	 * Deleting festival
	 */
	@RequestMapping(value = "/{id}/delete")
	public String deleteFestival(@PathVariable String id, Model model, final RedirectAttributes redirectAttributes) {
		service.deleteFestival(id);

		redirectAttributes.addFlashAttribute("festivals", service.getFestivals());
		return "redirect:/festivals";
	}

	/*
	 * 
	 * Adding performers
	 */
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

	/*
	 * Adding festival
	 * 
	 */
	@RequestMapping(value = "/addfest", method = RequestMethod.GET)
	public String addFestival(Model model) {
		Festival fest = new Festival();
		model.addAttribute("festival", fest);
		model.addAttribute("perfs", performerService.getPerformers());
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

	/*
	 * 
	 * Change festival info
	 */
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

	@RequestMapping(value = "/{festid}/deleteperf/{perfid}")
	public String removePerformer(@PathVariable String festid, Model model, @PathVariable String perfid, final RedirectAttributes redirectAttributes) {
		service.deletePerformer(festid, perfid);
		Festival fest = service.getFestival(festid);
		redirectAttributes.addFlashAttribute("festival", fest);
		redirectAttributes.addFlashAttribute("performers", fest.getPerformers());
		return "redirect:/festivals/{festid}";
	}

}

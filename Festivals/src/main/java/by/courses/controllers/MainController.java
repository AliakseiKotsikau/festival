package by.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.courses.repositories.ParticipantRepository;
import by.courses.service.FestivalService;
import by.courses.service.LoginService;
import by.courses.validator.AppUserForm;

@Controller
public class MainController {

	private LoginService loginService;
	private FestivalService festivalService;
	private ParticipantRepository partRepository;

	public MainController(LoginService loginService, FestivalService festivalService, ParticipantRepository partRepository) {
		super();
		this.loginService = loginService;
		this.festivalService = festivalService;
		this.partRepository = partRepository;
	}

	@RequestMapping(value = "/logoutSuccesful", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("title", "Logout");
		return "login";
	}

	@RequestMapping({ "/start", "", "/" })
	public String userInfo(Model model) {
		model.addAttribute("userInfo", loginService.getUserInfo());
		model.addAttribute("festivals", festivalService.getUsersFestivals());
		return "index";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {

		model.addAttribute("users", partRepository.findAll());
		return "users";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {

		AppUserForm form = new AppUserForm();
		model.addAttribute("appUserForm", form);
		return "register";
	}

	/*
	 * @RequestMapping(value = "/register", method = RequestMethod.POST) public
	 * String saveRegister(Model model, //
	 * 
	 * @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
	 * BindingResult result, // final RedirectAttributes redirectAttributes) {
	 * 
	 * // Validate result if (result.hasErrors()) { return "registerPage"; }
	 * Participant part = null; try { part =
	 * loginService.createNewUser(appUserForm); } // Other error!! catch (Exception
	 * e) { model.addAttribute("errorMessage", "Error: " + e.getMessage()); return
	 * "registerPage"; }
	 * 
	 * return "redirect:/registerSuccessful"; }
	 */

}

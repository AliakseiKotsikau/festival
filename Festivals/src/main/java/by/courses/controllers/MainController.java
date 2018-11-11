package by.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.courses.model.Participant;
import by.courses.repositories.ParticipantRepository;
import by.courses.service.FestivalService;
import by.courses.service.LoginService;
import by.courses.validator.AppUserForm;
import by.courses.validator.UserValidator;

@Controller
public class MainController {

	private LoginService loginService;
	private FestivalService festivalService;
	private ParticipantRepository partRepository;

	@Autowired
	private UserValidator userValidator;

	public MainController(LoginService loginService, FestivalService festivalService, ParticipantRepository partRepository) {
		super();
		this.loginService = loginService;
		this.festivalService = festivalService;
		this.partRepository = partRepository;
	}

	// logout success
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

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == AppUserForm.class) {
			dataBinder.setValidator(userValidator);
		}
		// ...
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {

		model.addAttribute("users", partRepository.findAll());
		return "users";
	}

	@RequestMapping("/registerSuccessful")
	public String viewRegisterSuccessful(Model model) {

		return "registerSuccessful";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {

		AppUserForm form = new AppUserForm();
		model.addAttribute("appUserForm", form);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		// Validate result
		if (result.hasErrors()) {
			return "register";
		}
		Participant newUser = null;
		try {
			newUser = loginService.createNewUser(appUserForm);
		}
		// Other error!!
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "register";
		}

		redirectAttributes.addFlashAttribute("flashUser", newUser);

		return "redirect:/registerSuccessful";
	}

}

package by.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.courses.service.FestivalService;
import by.courses.service.LoginService;

@Controller
public class MainController {

	private LoginService loginService;
	private FestivalService festivalService;

	public MainController(LoginService loginService, FestivalService festivalService) {
		super();
		this.loginService = loginService;
		this.festivalService = festivalService;
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

}

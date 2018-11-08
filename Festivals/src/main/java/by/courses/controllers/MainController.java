package by.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/logoutSuccesful", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("title","Logout");
		return "login";
	}
	

}

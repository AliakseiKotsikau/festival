package by.courses.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.courses.model.Login;
import by.courses.model.Participant;
import by.courses.repositories.FestivalRepository;
import by.courses.repositories.LoginRepository;
import by.courses.repositories.ParticipantRepository;

@Service
public class LoginService {

	private FestivalRepository festRepository;
	private ParticipantRepository participantRepository;
	private LoginRepository loginRepository;

	public LoginService(FestivalRepository festRepository, ParticipantRepository participantRepository, LoginRepository loginRepository) {
		super();
		this.festRepository = festRepository;
		this.participantRepository = participantRepository;
		this.loginRepository = loginRepository;
	}

	public String getUserInfo() {
		UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Login login = loginRepository.findByUsername(userdetails.getUsername());
		Participant part = participantRepository.findByUser_id(login.getUser_id());
		String greeting = "Hello, " + part.getFirstName() + " (" + userdetails.getAuthorities().toString() + "), here is your festivals:";

		return greeting;
	}

}

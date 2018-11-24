package by.courses.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.courses.model.Login;
import by.courses.model.Participant;
import by.courses.model.Role;
import by.courses.repositories.FestivalRepository;
import by.courses.repositories.LoginRepository;
import by.courses.repositories.ParticipantRepository;
import by.courses.repositories.RoleRepository;
import by.courses.validator.AppUserForm;

@Service
public class LoginService {

	private FestivalRepository festRepository;
	private ParticipantRepository participantRepository;
	private LoginRepository loginRepository;
	private RoleRepository roleRepository;

	public LoginService(FestivalRepository festRepository, ParticipantRepository participantRepository, LoginRepository loginRepository, RoleRepository roleRepository) {
		super();
		this.festRepository = festRepository;
		this.participantRepository = participantRepository;
		this.loginRepository = loginRepository;
		this.roleRepository = roleRepository;
	}

	public String getUserInfo() {
		// checks for logged user
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			// returns user name and roles
			if (obj instanceof UserDetails) {
				UserDetails userdetails = (UserDetails) obj;
				Login login = loginRepository.findByUsername(userdetails.getUsername());
				Participant part = participantRepository.findByUser_id(login.getUser_id());
				String greeting = "Hello, " + part.getFirstName() + " (" + userdetails.getAuthorities().toString() + "), here is your festivals:";
				return greeting;
			} else
				return obj.toString();

		}

		else
			return null;
	}

	public Set<Role> getCurentUserRoles() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (obj instanceof UserDetails) {
				UserDetails userdetails = (UserDetails) obj;
				Login login = loginRepository.findByUsername(userdetails.getUsername());
				return login.getRoles();
			} else
				return new HashSet<>();
		} else
			return new HashSet<>();
	}

	public Participant createNewUser(AppUserForm form) {

		// Get user Role
		Role role = roleRepository.findByRole("ROLE_USER");

		// Create new Login
		Login login = new Login();
		login.setUsername(form.getUserName());
		login.setPassword(form.getPassword());
		login.getRoles().add(role);

		// Create participant
		Participant part = new Participant();
		part.setAge(form.getAge());
		part.setEmail(form.getEmail());
		part.setFirstName(form.getFirstName());
		part.setLastName(form.getLastName());
		part.setPhone(form.getPhone());

		Login savedLogin = loginRepository.save(login);
		part.setUser_id(savedLogin.getUser_id());
		return participantRepository.save(part);
	}

	public void makeAdmin(String id) {
		Participant part = participantRepository.findById(Long.parseLong(id)).get();
		Login login = loginRepository.findById(part.getUser_id()).get();
		Role role = roleRepository.findByRole("ROLE_ADMIN");
		login.getRoles().add(role);

		loginRepository.save(login);

	}

}

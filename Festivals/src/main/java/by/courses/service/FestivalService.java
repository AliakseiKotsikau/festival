package by.courses.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.courses.model.Festival;
import by.courses.model.Login;
import by.courses.model.Participant;
import by.courses.repositories.FestivalRepository;
import by.courses.repositories.LoginRepository;
import by.courses.repositories.ParticipantRepository;

@Service
public class FestivalService {

	private FestivalRepository festRepository;
	private ParticipantRepository participantRepository;
	private LoginRepository loginRepository;

	public FestivalService(FestivalRepository festRepository, ParticipantRepository participantRepository, LoginRepository loginRepository) {
		super();
		this.festRepository = festRepository;
		this.participantRepository = participantRepository;
		this.loginRepository = loginRepository;
	}

	public Iterable<Festival> getFestivals() {
		return festRepository.findAll();
	}

	public Festival getFestival(String id) {
		return festRepository.findById(Long.parseLong(id)).get();
	}

	/**
	 * add's logged user to Fest's perticipants
	 * 
	 * @param festId
	 */
	public void addParticpipant(String festId) {
		Festival fest = festRepository.findById(Long.parseLong(festId)).get();
		UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Login login = loginRepository.findByUsername(userdetails.getUsername());
		Participant part = participantRepository.findByUser_id(login.getUser_id());
		fest.getParticipants().add(part);
		part.getFestivals().add(fest);
		festRepository.save(fest);
		participantRepository.save(part);
	}

	public Iterable<Festival> getUsersFestivals() {
		UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Login login = loginRepository.findByUsername(userdetails.getUsername());
		Participant part = participantRepository.findByUser_id(login.getUser_id());

		return part.getFestivals();
	}

}

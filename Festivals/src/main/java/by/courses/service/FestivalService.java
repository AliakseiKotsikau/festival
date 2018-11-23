package by.courses.service;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.courses.model.Festival;
import by.courses.model.Login;
import by.courses.model.Participant;
import by.courses.model.Performer;
import by.courses.repositories.FestivalRepository;
import by.courses.repositories.LoginRepository;
import by.courses.repositories.ParticipantRepository;
import by.courses.repositories.PerformerRepository;

@Service
public class FestivalService {

	private static final String Set = null;
	private FestivalRepository festRepository;
	private ParticipantRepository participantRepository;
	private LoginRepository loginRepository;
	private PerformerRepository performerRepository;

	public FestivalService(FestivalRepository festRepository, ParticipantRepository participantRepository, LoginRepository loginRepository, PerformerRepository performerRepository) {
		super();
		this.festRepository = festRepository;
		this.participantRepository = participantRepository;
		this.loginRepository = loginRepository;
		this.performerRepository = performerRepository;
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
		if (fest.getParticipants().size() == fest.getSeating()) {
			throw new NumberFormatException("No places left.");
		}
		UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Login login = loginRepository.findByUsername(userdetails.getUsername());
		Participant part = participantRepository.findByUser_id(login.getUser_id());
		fest.getParticipants().add(part);
		part.getFestivals().add(fest);
		festRepository.save(fest);
		participantRepository.save(part);
	}

	public Iterable<Festival> getUsersFestivals() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (obj instanceof UserDetails) {
				UserDetails userdetails = (UserDetails) obj;
				Login login = loginRepository.findByUsername(userdetails.getUsername());
				Participant part = participantRepository.findByUser_id(login.getUser_id());
				return part.getFestivals();
			}
		}
		return new ArrayList<Festival>();
	}

	public void saveNewPerformer(String festId, Performer perf) {
		if (!perf.getName().isBlank()) {
			Festival fest = festRepository.findById(Long.parseLong(festId)).get();
			fest.getPerformers().add(perf);
			perf.getFestivals_perf().add(fest);
			festRepository.save(fest);
			performerRepository.save(perf);
		}

	}

	public Festival saveNewFestival(Festival fest) {
		if (festRepository.findByName(fest.getName()) != null) {
			return null;
		}
		Festival saved = festRepository.save(fest);
		java.util.Set<Performer> setFest = new HashSet<>(fest.getPerformers()); // to avoid ConcurrentExc

		for (Performer perf : setFest) {
			Performer loaded = performerRepository.findById(perf.getPerformer_id()).get();
			loaded.getFestivals_perf().add(saved);
			performerRepository.save(loaded);
		}

		return saved;
	}

	public Festival changeFestival(Festival fest) {
		Festival changing = festRepository.findByName(fest.getName());
		changing.setDate(fest.getDate());
		changing.setPlace(fest.getPlace());
		changing.setSeating(fest.getSeating());
		festRepository.save(changing);
		return changing;
	}

	public void deleteFestival(String festId) {
		Festival fest = festRepository.findById(Long.parseLong(festId)).get();
		for (Performer perf : performerRepository.findAll()) {
			perf.getFestivals_perf().remove(fest);
			performerRepository.save(perf);
		}

		for (Participant part : participantRepository.findAll()) {
			part.getFestivals().remove(fest);
			participantRepository.save(part);
		}

		festRepository.deleteById(Long.parseLong(festId));
	}

	public void deletePerformer(String festId, String perfId) {
		Festival fest = festRepository.findById(Long.parseLong(festId)).get();
		Performer perf = performerRepository.findById(Long.parseLong(perfId)).get();
		fest.getPerformers().remove(perf);
		perf.getFestivals_perf().remove(fest);
		performerRepository.save(perf);
		festRepository.save(fest);
	}

}

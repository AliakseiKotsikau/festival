package by.courses.service;

import org.springframework.stereotype.Service;

import by.courses.model.Performer;
import by.courses.repositories.PerformerRepository;

@Service
public class PerformerService {

	private PerformerRepository performerRepository;

	public PerformerService(PerformerRepository performerRepository) {
		super();
		this.performerRepository = performerRepository;
	}

	public Iterable<Performer> getPerformers() {

		return performerRepository.findAll();
	}

}

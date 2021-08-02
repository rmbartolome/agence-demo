package com.rmbartolome.agence.security.services;

import com.rmbartolome.agence.models.Travel;
import com.rmbartolome.agence.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TravelsService {

	@Autowired
	TravelRepository travelRepository;

	public Travel create(Travel travel) {

		travel.setStartDate(new Date());
		return travelRepository.save(travel);
	}

	public Travel deleteTravel(Travel travel) {
		travel.setFinishDate(new Date());
		return (Travel) travelRepository.save(travel);
	}

	public List<Travel> findTravels() {
		//TODO Mejorar
		return travelRepository.findAll();
	}

	public List<Travel> findTravelsByMonthAndYear(LocalDate begin, LocalDate end) {

		return travelRepository.getAllBetweenDates(begin.atStartOfDay(), end.atStartOfDay());
	}
}

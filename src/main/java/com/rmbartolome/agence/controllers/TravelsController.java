package com.rmbartolome.agence.controllers;

import com.rmbartolome.agence.exception.ResourceNotFoundException;
import com.rmbartolome.agence.models.Travel;
import com.rmbartolome.agence.models.Vehicle;
import com.rmbartolome.agence.models.Worker;
import com.rmbartolome.agence.security.services.TravelsService;
import com.rmbartolome.agence.security.services.VehiclesService;
import com.rmbartolome.agence.security.services.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelsController {
	
	@Autowired
	TravelsService travelService;
	
	@Autowired
	WorkersService workerService;
	
	@Autowired
	VehiclesService vehiclesService;
	
	@PostMapping(path="/travel/{idWorker}/{idVehicle}")
	private ResponseEntity<Travel> saveViaje(@PathVariable("idWorker") Integer idWorker,@PathVariable("idVehicle") Integer idVehicle)throws ResourceNotFoundException {
		Worker worker = workerService.findById(idWorker);
		
		Vehicle vehicles = vehiclesService.findById(idVehicle);

		Travel newTravel = new Travel();
		newTravel.setUsuarioId(worker);
		newTravel.setVehicleId(vehicles);

		Travel temporal = travelService.create(newTravel);
		
		try {
			return ResponseEntity.created(new URI("/api/viaje"+temporal.getId())).body(temporal);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
		
	}			
	
	@DeleteMapping(path="/viagem/{idWorker}/{idVehicle}")
	private ResponseEntity<Travel> deleteTravel(@PathVariable("idWorker") Integer idWorker,@PathVariable("idVehicle") Integer idVehicle)throws ResourceNotFoundException {
		Worker worker = workerService.findById(idWorker);
		Vehicle vehicle = vehiclesService.findById(idVehicle);
		Travel temporal = new Travel();

		List<Travel> travels = travelService.findTravels();

		for(Travel travel: travels) {
			if(travel.getUsuarioId().equals(worker) &&
					travel.getVehicleId().equals(vehicle) &&
					travel.getFinishDate() == null) {
				temporal = travel;
				break;
			}
		}

		temporal.setUsuarioId(worker);
		temporal.setVehicleId(vehicle);

		temporal = travelService.deleteTravel(temporal);

		try {
			return ResponseEntity.ok().body(temporal);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	

	@GetMapping(path="travel/finish/{month}/{year}")
	private ResponseEntity<List<Travel>> findTravelsByDate(@PathVariable("month") Integer month,@PathVariable("year") Integer year) {

		LocalDate initial = LocalDate.of(year, month, 01);
		LocalDate start = initial.withDayOfMonth(1);
		LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());

		return ResponseEntity.ok(travelService.findTravelsByMonthAndYear(start, end));
	}
		
	

}

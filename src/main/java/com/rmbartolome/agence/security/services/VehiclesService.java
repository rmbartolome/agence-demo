package com.rmbartolome.agence.security.services;

import com.rmbartolome.agence.exception.ResourceNotFoundException;
import com.rmbartolome.agence.models.Vehicle;
import com.rmbartolome.agence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclesService {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	public Vehicle createVehiculo(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public void deleteVehiculo(Integer vehiculoId) {
		vehicleRepository.deleteById(vehiculoId);;
	}
		
	public List<Vehicle> getAllVehiculos() {
		return vehicleRepository.findAll();
	}
	
	public Vehicle findById(Integer id) throws ResourceNotFoundException {
		return (Vehicle) vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
	}
}

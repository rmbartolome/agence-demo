package com.rmbartolome.agence.controllers;

import com.rmbartolome.agence.models.Vehicle;
import com.rmbartolome.agence.security.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name="/api")
public class VehiclesController {
	@Autowired
	VehiclesService vehiculoService;
	
	@PostMapping("/vehicles")
	private ResponseEntity<Vehicle> saveVehiculo (@RequestBody Vehicle vehiculo) {
		Vehicle temporal = vehiculoService.createVehiculo(vehiculo);
		
		try {
			return ResponseEntity.created(new URI("/api/vehiculo"+temporal.getId())).body(temporal);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
		
	}
	
	@DeleteMapping(path="vehicles/{id}")
	private ResponseEntity<Void> deleteVehiculo(@PathVariable("id") Integer vehiculoId) {
		vehiculoService.deleteVehiculo(vehiculoId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/vehicles")
	private ResponseEntity<List<Vehicle>> findVehiculos() {
		  return ResponseEntity.ok(vehiculoService.getAllVehiculos());
	}
	
	//TODO vehiculos retirados
	/*@GetMapping(path="/retirados")
	private ResponseEntity<Vehiculo> findVehiculoRetirados() { 
		  return ResponseEntity.ok(vehiculoService.getVehiculosRetirados());
	}*/
	

}

package com.rmbartolome.agence.controllers;

import com.rmbartolome.agence.models.Vehicle;
import com.rmbartolome.agence.security.services.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {
	@Autowired
	VehiclesService vehiculoService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Vehicle> saveVehiculo (@RequestBody Vehicle vehiculo) {
		Vehicle temporal = vehiculoService.createVehiculo(vehiculo);
		
		try {
			return ResponseEntity.created(new URI("/api/vehiculo"+temporal.getId())).body(temporal);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteVehiculo(@PathVariable("id") Integer vehiculoId) {
		vehiculoService.deleteVehiculo(vehiculoId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Vehicle>> findVehiculos() {
		  return ResponseEntity.ok(vehiculoService.getAllVehiculos());
	}
}

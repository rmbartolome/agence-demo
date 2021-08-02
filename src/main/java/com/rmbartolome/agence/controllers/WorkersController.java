package com.rmbartolome.agence.controllers;

import com.rmbartolome.agence.exception.ResourceNotFoundException;
import com.rmbartolome.agence.models.Worker;
import com.rmbartolome.agence.security.services.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(name="/api")
public class WorkersController {
	
	@Autowired
	WorkersService workersService;
	
	@PostMapping("/workers/add")
	private ResponseEntity<Worker> saveWorker (@RequestBody Worker newWorker) {
		newWorker.setEnabled(true);
		Worker temporal = workersService.create(newWorker);
		
		try {
			return ResponseEntity.ok().body(temporal);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}		
		
	}	
	
	@GetMapping("/workers")
	private ResponseEntity<List<Worker>> getWorkers() {
		  return ResponseEntity.ok(workersService.getAllTrabajadores());
	}


	@DeleteMapping("/workers/{id}")
	private ResponseEntity<Worker> deleteWorker(@PathVariable(value = "id") Integer trabajadorId) throws ResourceNotFoundException {
		Worker temporal = workersService.deleteTrabajador(trabajadorId);
		return new ResponseEntity<>(temporal, HttpStatus.OK);
	}
}

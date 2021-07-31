package com.rmbartolome.agence.services;

import com.rmbartolome.agence.exception.ResourceNotFoundException;
import com.rmbartolome.agence.models.Worker;
import com.rmbartolome.agence.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkersService {
	
	@Autowired
	WorkerRepository workerRepository;
	
	public Worker create(Worker worker) {
		return workerRepository.save(worker);
	}
	
	public Worker deleteTrabajador(Integer trabajadorId) throws ResourceNotFoundException {
		Worker temporal = (Worker) workerRepository.findById(trabajadorId)
				.orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + trabajadorId));
		temporal.setEnabled(false);
		workerRepository.delete(temporal);
		return temporal;
	}
	
	public List<Worker> getAllTrabajadores() {
		return workerRepository.findAll();
	}

	public Worker findById(Integer id) throws ResourceNotFoundException {
		return (Worker) workerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + id));
	}
	
}

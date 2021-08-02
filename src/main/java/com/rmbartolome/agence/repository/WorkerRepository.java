package com.rmbartolome.agence.repository;

import com.rmbartolome.agence.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

}

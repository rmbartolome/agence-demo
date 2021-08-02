package com.rmbartolome.agence.repository;

import com.rmbartolome.agence.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

}

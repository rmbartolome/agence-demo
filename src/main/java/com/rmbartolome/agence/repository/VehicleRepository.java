package com.rmbartolome.agence.repository;


import com.rmbartolome.agence.models.Vehicle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{

}

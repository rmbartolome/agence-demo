package com.rmbartolome.agence.repository;

import com.rmbartolome.agence.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel,Integer>{
    @Query(value = "SELECT * FROM TRAVELS WHERE FINISH_DATE >= :startDate AND FINISH_DATE <= :endDate", nativeQuery = true)
    List<Travel> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

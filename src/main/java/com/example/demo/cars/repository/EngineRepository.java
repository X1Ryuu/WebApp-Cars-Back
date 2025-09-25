package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> findEnginesByGeneration_IdOrderByName(Long id);
    List<Engine> findEnginesByModel_IdOrderByName(Long id);
    List<Engine> findEnginesByVersion_IdOrderByName(Long id);
}

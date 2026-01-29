package com.example.demo.car.repository;

import com.example.demo.car.model.archive.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
    List<Generation> findAllByModel_NameOrderByName(String name);
    List<Generation> findGenerationsByModel_IdOrderByName(Long id);
}

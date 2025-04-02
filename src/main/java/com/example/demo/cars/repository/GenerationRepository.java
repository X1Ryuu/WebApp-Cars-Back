package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
    Generation findByName(String name);
    List<Generation> findAllByModel_Id(Long id);
    List<Generation> findAllByModel_Name(String name);
}

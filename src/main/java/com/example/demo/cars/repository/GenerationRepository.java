package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.model.archive.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
    List<Generation> findAllByModel_NameOrderByName(String name);
}

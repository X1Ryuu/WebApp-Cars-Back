package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GenerationRepository extends JpaRepository<Generation, Long> {
}

package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.model.archive.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> findByName(String name);
    Optional<Version> findByModel(Model model);
}

package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.model.archive.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    Version findByName(String name);

    List<Version> findAllByModel_Id(Long id);
    List<Version> findAllByGeneration_Id(Long id);

    List<Version> findAllByGeneration_Name(String name);
    List<Version> findAllByModel_Name(String name);
}

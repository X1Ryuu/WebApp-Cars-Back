package com.example.demo.car.repository;

import com.example.demo.car.model.archive.Model;
import com.example.demo.car.model.archive.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> findByName(String name);
    Optional<Version> findByModel(Model model);
    List<Version> findAllByGeneration_NameOrderByName(String name);
    List<Version> findAllByModel_NameOrderByName(String name);
    List<Version> findVersionsByModel_IdOrderByName(Long id);
    List<Version> findVersionsByGeneration_IdOrderByName(Long id);
}

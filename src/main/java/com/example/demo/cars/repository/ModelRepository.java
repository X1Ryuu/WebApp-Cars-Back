package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Model;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findAllByBrand_NameOrderByName(String name);
    List<Model> findModelsByBrand_IdOrderByName(Long id);
}

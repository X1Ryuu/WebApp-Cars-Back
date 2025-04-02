package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByName(String name);

    List<Model> findAllByBrand_Id(Long id);
    List<Model> findAllByBrand_Name(String name);
}

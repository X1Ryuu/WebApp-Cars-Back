package com.example.demo.cars.repository;

import com.example.demo.cars.model.archive.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);


    List<Brand> findBrandsByOrderByName();
    void deleteById(Long id);


}
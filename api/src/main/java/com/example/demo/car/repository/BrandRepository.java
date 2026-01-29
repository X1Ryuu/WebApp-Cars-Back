package com.example.demo.car.repository;

import com.example.demo.car.model.archive.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);


    List<Brand> findBrandsByOrderByName();
    void deleteById(Long id);


}
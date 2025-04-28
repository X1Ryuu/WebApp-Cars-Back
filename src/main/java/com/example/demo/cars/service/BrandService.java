package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Brand;
import com.example.demo.cars.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {


    BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){this.brandRepository = brandRepository;}

    public Brand addBrand(Brand brand) {return brandRepository.save(brand);}
    public void deleteBrand(long id) {brandRepository.deleteById(id);}
    public List<Brand> findAllBrands(){return brandRepository.findAllByOrderByName();}
    public Brand findByName(String name){return brandRepository.findByName(name);}
    public Brand updateBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    public Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }


}

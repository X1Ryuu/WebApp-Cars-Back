package com.example.demo.car.service;

import com.example.demo.car.model.archive.Brand;
import com.example.demo.car.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {


    BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){this.brandRepository = brandRepository;}

    public Brand addBrand(Brand brand) {return brandRepository.save(brand);}
    public void deleteBrand(long id) {brandRepository.deleteById(id);}
    public List<Brand> findAllBrands(){return brandRepository.findBrandsByOrderByName();}
    public Brand findByName(String name){return brandRepository.findByName(name);}
    public Brand updateBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    public Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }


}

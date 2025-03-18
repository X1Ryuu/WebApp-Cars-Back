package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Brand;
import com.example.demo.cars.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){this.brandRepository = brandRepository;}
    public Brand addBrand(Brand brand) {return brandRepository.save(brand);}
    public void deleteBrand(long id) {brandRepository.deleteById(id);}
    public List<Brand> findAllBrands(){return brandRepository.findAll();}
    public Optional<Brand> findByName(String name){return brandRepository.findByName(name);}
    public Brand updateBrand(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    public Brand findBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }
    public Brand findBrandByName(String name) {
        for(Brand brand : brandRepository.findAll()) {
       //     System.out.println(brand.getName().toLowerCase()+", "+name.toLowerCase());
            if(brand.getName().equalsIgnoreCase(name)) {
                return brand;
            }
        }
        return null;
        //return Optional.empty();
    }
}

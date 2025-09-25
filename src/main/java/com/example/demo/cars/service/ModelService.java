package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository){this.modelRepository = modelRepository;}

    public List<Model> findModelsByBrandName(String name){
        return modelRepository.findAllByBrand_NameOrderByName(name);
    }



    public List<Model> findModelsByBrandId(Long id){
        return modelRepository.findModelsByBrand_IdOrderByName(id);
    }

    public void addModel(Model model){
        this.modelRepository.save(model);
    }


    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }
}

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
    public List<Model> getModelsByBrandNameId(String nameId){
        List<Model> models = new java.util.ArrayList<>();
        for(Model model : findAllModels()){
            if(model.getBrand().getName().toLowerCase().equals(nameId))models.add(model);
        }
        return models;
    }

    public List<Model> getModelsByBrandName(String name){
        return modelRepository.findAllByBrand_NameOrderByName(name);
    }



    public List<Model> getModelsByBrandId(Long id){
        List<Model> models = new java.util.ArrayList<>();
        for(Model model : findAllModels()){
            if(model.getBrand().getId().equals(id))models.add(model);
        }
        return models;
    }

    public void addModel(Model model){
        this.modelRepository.save(model);
    }


    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }
}

package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository){this.modelRepository = modelRepository;}



    public List<Model> getModelsByBrandId(Long id){
        return modelRepository.findAllByBrand_Id(id);
    }

    public void addModel(Model model){
        this.modelRepository.save(model);
    }

    public Model findByName(String name){return modelRepository.findByName(name);}


    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    public List<Model> getModelsByBrandName(String name){
        return modelRepository.findAllByBrand_Name(name);
    }
}

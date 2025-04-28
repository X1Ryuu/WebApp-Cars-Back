package com.example.demo.cars.controller;

import com.example.demo.cars.dto.ModelDTO;
import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.service.BrandService;
import com.example.demo.cars.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/models")
public class ModelController {

    ModelService modelService;
    BrandService brandService;
    @Autowired
    public ModelController(ModelService modelService, BrandService brandService){this.modelService = modelService;
        this.brandService = brandService;}

    @GetMapping("/all")
    public ResponseEntity<List<Model>>  getAllModels(){
        List<Model> models = modelService.findAllModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }


    @GetMapping("/{brandName}")
    public ResponseEntity<List<Model>> getModelsByBrandName(@PathVariable String brandName){

        List<Model> models = modelService.getModelsByBrandName(brandName);

        System.out.println("models: "+models);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addModel(@RequestBody ModelDTO modelDTO){

        Model model = new Model();
        model.setName(modelDTO.getName());

        System.out.println(model);
        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }


}

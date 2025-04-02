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

    ModelController(ModelService modelService, BrandService brandService){
        this.modelService=modelService;
        this.brandService=brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Model>>  getAllModels(){
        List<Model> models = modelService.findAllModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
/*    @GetMapping("/{brandId}")
    public ResponseEntity<List<Model>> getModelsByBrandId(@PathVariable Long brandId) {
        List<Model> models = modelService.getModelsByBrandId(brandId);

        return new ResponseEntity<>(models, HttpStatus.OK);
    }*/
    @GetMapping("/{name}")
    public ResponseEntity<List<Model>> getModelsByBrandName(@PathVariable String name) {
        System.out.println(name);
        return new ResponseEntity<>(modelService.getModelsByBrandName(name), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<?> addModel(@RequestBody ModelDTO modelDTO){
        System.out.println(modelDTO);
        Model model = new Model();
        model.setName(modelDTO.getName());
        model.setBrand(brandService.findByName(modelDTO.getBrandName()));
        modelService.addModel(model);
        System.out.println(model);
        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }

    private String formatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        return day + "." + month + "." + year;
    }
}

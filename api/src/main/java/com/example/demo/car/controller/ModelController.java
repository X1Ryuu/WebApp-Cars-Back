package com.example.demo.car.controller;

import com.example.demo.car.dto.ModelDTO;
import com.example.demo.car.model.archive.Model;
import com.example.demo.car.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    ModelService modelService;
    @Autowired
    public ModelController(ModelService modelService){this.modelService = modelService;
}

    @GetMapping("/all")
    public ResponseEntity<List<Model>>  getAllModels(){
        List<Model> models = modelService.findAllModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }


    @GetMapping("/{brandId}")
    public ResponseEntity<List<Model>> getModelsByBrandName(@PathVariable Long brandId) {
        return new ResponseEntity<>(modelService.findModelsByBrandId(brandId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addModel(@RequestBody ModelDTO modelDTO){

        Model model = new Model();
        model.setName(modelDTO.getName());

        System.out.println(model);
        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }


}

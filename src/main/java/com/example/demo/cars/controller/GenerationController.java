package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.service.GenerationService;
import com.example.demo.cars.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/generations")
public class GenerationController {


    GenerationService generationService;
    ModelService modelService;

    GenerationController(GenerationService generationService, ModelService modelService){
        this.generationService=generationService;
        this.modelService = modelService;
    }

/*    @GetMapping("/{modelId}")
    public ResponseEntity<List<Generation>> getGenerationsByModel(@PathVariable Long modelId){
        List<Generation> generations = generationService.
        return new ResponseEntity<>(models, HttpStatus.OK);
    }*/

    @GetMapping("/{modelName}")
    public ResponseEntity<List<Generation>> getGenerationsByModelName(@PathVariable String modelName){
        return new ResponseEntity<>(generationService.findGenerationsByModelName(modelName), HttpStatus.OK);
    }


/*    @GetMapping("/{modelId}")//lista generacji dla danego modelu
    ResponseEntity<List<Generation>> getGenerationsByModelId(@PathVariable Long modelId){
        List<Generation> generations = generationService.getGenerationsByModelId(modelId);
        return new ResponseEntity<>(generations, HttpStatus.OK);
    }*/


}

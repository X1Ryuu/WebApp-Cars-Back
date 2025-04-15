package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.service.GenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generations")
public class GenerationController {
    GenerationService generationService;

    public GenerationController(GenerationService generationService) {
        this.generationService = generationService;
    }

    @GetMapping("/{modelName}")
    public ResponseEntity<List<Generation>> getGenerationsByModelName(@PathVariable String modelName){
        return new ResponseEntity<>(generationService.findGenerationsByModelName(modelName), HttpStatus.OK);
    }
}

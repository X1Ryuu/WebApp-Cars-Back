package com.example.demo.car.controller;

import com.example.demo.car.model.archive.Generation;
import com.example.demo.car.service.GenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generations")
public class GenerationController {
    GenerationService generationService;

    public GenerationController(GenerationService generationService) {
        this.generationService = generationService;
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<List<Generation>> getGenerationsByModelId(@PathVariable Long modelId){
        return new ResponseEntity<>(generationService.findGenerationsByModelId(modelId), HttpStatus.OK);
    }
}

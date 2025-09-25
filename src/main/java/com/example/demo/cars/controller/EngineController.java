package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Engine;
import com.example.demo.cars.service.EngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/engines")
public class EngineController {
    EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/gens/{genId}")
    public ResponseEntity<List<Engine>> getEnginesByGeneration(@PathVariable Long genId){
        List<Engine> list = engineService.findEnginesByGeneration(genId);
        System.out.println("Generations");

        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/mods/{modId}")
    public ResponseEntity<List<Engine>> getEnginesByModel(@PathVariable Long modId){
        List<Engine> list = engineService.findEnginesByModel(modId);
        System.out.println("Mods");

        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/vers/{verId}")
    public ResponseEntity<List<Engine>> getEnginesByVersion(@PathVariable Long verId){
        List<Engine> list = engineService.findEnginesByVersion(verId);
        System.out.println("Version");
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

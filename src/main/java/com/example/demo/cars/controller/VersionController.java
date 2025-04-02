package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Version;
import com.example.demo.cars.repository.VersionRepository;
import com.example.demo.cars.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/versions")
public class VersionController {


    VersionService versionService;


    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

/*    @GetMapping("/all")
    public ResponseEntity<List<Brand>> getAllVersions(){
        List<Version> versions = versionService.findAllVersions();
        versions.sort(new BrandComparator());
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }*/

    @GetMapping("/gen/{generationName}")
    public ResponseEntity<List<Version>> getVersionsByGeneration(@PathVariable String generationName){
        return new ResponseEntity<>(versionService.getVersionsByGenerationName(generationName), HttpStatus.OK);
    }

    @GetMapping("/model/{modelName}")
    public ResponseEntity<List<Version>> getVersionsByModel(@PathVariable String modelName){
        return new ResponseEntity<>(versionService.getVersionsByModelName(modelName), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVersion(@RequestBody Version version){
/*        newVersion.setName(versionName);
        newVersion.setStartYear(formatDate(versionStartYear));
        newVersion.setEndYear(formatDate(versionEndYear));*/
        System.out.println(version);
        //versionService.addVersion(version);
        return new ResponseEntity<>(version, HttpStatus.CREATED);
/*        return ResponseEntity.status(HttpStatus.CREATED).body("Brand added successfully!");*/
    }
}

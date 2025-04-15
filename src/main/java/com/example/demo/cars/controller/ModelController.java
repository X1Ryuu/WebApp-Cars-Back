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
/*    @GetMapping("/{brandId}")
    public ResponseEntity<List<Model>> getModelsByBrandId(@PathVariable Long brandId){

        List<Model> models = modelService.getModelsByBrandId(brandId);

        System.out.println("models: "+models);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }*/

    @GetMapping("/{brandName}")
    public ResponseEntity<List<Model>> getModelsByBrandName(@PathVariable String brandName){

        List<Model> models = modelService.getModelsByBrandName(brandName);

        System.out.println("models: "+models);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addModel(@RequestBody ModelDTO modelDTO){
/*        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        //Brand newBrand = brand;
        String user = jwt.getUsername();

        if (user != null) {
            System.out.println("Authenticated user: " + user);
        }*/
        //System.out.println(modelDTO);
        Model model = new Model();
        model.setName(modelDTO.getName());
        model.setBrand(brandService.findBrandById(modelDTO.getBrandId()));
     //   model.setNameId(modelDTO.getName().toLowerCase());
/*        model.setEndYear(modelDTO.getEndYear());
        model.setStartYear(modelDTO.getStartYear());*/
  //      System.out.println(modelDTO+", "+model+", "+modelDTO.getBrandId());
        //model.setBrand(brandService.findByNameId(modelDTO.getBrandId()));
   //     Brand brand = brandService.findByNameId(modelDTO.getBrandId());
/*        System.out.println(model);
        System.out.println(modelDTO);*/

       // System.out.println(model);
        modelService.addModel(model);

        //model.setNameId(modelDTO.getName().toLowerCase());
      //  System.out.println(model);
        //    Brand newBrand = brandService.addBrand(brand);
        //eturn new ResponseEntity<>(newBrand, HttpStatus.CREATED);
        /*return ResponseEntity.status(HttpStatus.CREATED).body("Brand added successfully!");*/
        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }

    private String formatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        return day + "." + month + "." + year;
    }
}

package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Brand;
import com.example.demo.cars.service.BrandService;

import com.example.demo.jwt.CustomJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/brands")
public class BrandController {

    BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.findAllBrands();
        //System.out.println(brands);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }


/*    @GetMapping("/map")
    public ResponseEntity<Map<String, Long>> getBrandsMap() {
        List<Brand> brands = brandRepository.findAll();

        // Tworzymy mapę: nameId -> id
        Map<String, Long> brandMap = brands.stream()
                .collect(Collectors.toMap(Brand::getNameId, Brand::getId));

        return ResponseEntity.ok(brandMap);
    }*/
    @GetMapping("/{brandName}")
    public ResponseEntity<Brand> getBrandByName(@PathVariable("brandName") String brandName) {
        /*Brand brand = brandService.findBrandByName(brandName);
        return new ResponseEntity<>(brand, HttpStatus.OK);*/
        return new ResponseEntity<>(brandService.findByName(brandName), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addBrand(@RequestBody Brand brand){

        System.out.println(brand);
        brandService.addBrand(brand);

        return new ResponseEntity<>(brand, HttpStatus.CREATED);

    }

    @GetMapping("/hello")
    public Message hello(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated User: " + authentication.getName());

        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        //  System.out.println(jwt);
        var message = MessageFormat.format("Hello {0}", jwt.getUsername());
        return new Message(message);
    }

    record Message(String message){}

    @PutMapping("/update")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand){
        //Brand newBrand = brandService.updateBrand(brand);
        Brand newBrand = brand;
        System.out.println(brand);
        return new ResponseEntity<>(newBrand, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id){
        brandService.deleteBrand(id);
        return new ResponseEntity<>("Brand deleted", HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Brand> getBrandByName(@PathVariable("id") Long id) {
        Brand brand = brandService.findBrandById(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }


/*    @GetMapping("")
    public String getCarBrands(Model model) {
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brands", brands);
        return "cars/car-brands";
    }

    @PostMapping("/add")
    public String addBrand(@ModelAttribute Brand brand, Model model) {
        brandRepository.save(brand);
        return "redirect:/car-brands";
    }*/

}


package com.example.demo.cars.controller;

import com.example.demo.cars.model.archive.Brand;
import com.example.demo.cars.service.BrandService;
import com.example.demo.comparators.BrandComparator;
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
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.findAllBrands();
        brands.sort(new BrandComparator());
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
        Brand brand = brandService.findBrandByName(brandName);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addBrand(@RequestBody Brand brand){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
/*        System.out.println(authentication);
// Sprawdź, czy użytkownik jest uwierzytelniony
        if (!(authentication instanceof CustomJwt)) {
            System.out.println("Brak uwierzytelnienia lub użytkownik jest anonimowy.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Brak uprawnień");
        }*/

        CustomJwt jwt = (CustomJwt) authentication;
        String user = jwt.getUsername();
        System.out.println("Authenticated user: " + user);

  //      brand.setNameId(brand.getName().toLowerCase());
      //  brand.setLogo("logo");
        System.out.println(brand);
        brandService.addBrand(brand);
    //    Brand newBrand = brandService.addBrand(brand);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);

//        return ResponseEntity.ok(brandService.addBrand(brand));
        //   return ResponseEntity.status(HttpStatus.CREATED).body("Brand added successfully!");
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


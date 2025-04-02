package com.example.demo;

import com.example.demo.cars.model.archive.*;
import com.example.demo.cars.repository.BrandRepository;
import com.example.demo.cars.repository.GenerationRepository;
import com.example.demo.cars.repository.ModelRepository;
import com.example.demo.cars.repository.VersionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

@Configuration
public class DataInitializr {

    @Bean
    CommandLineRunner initDatabase(ModelRepository modelRepository, BrandRepository brandRepository, GenerationRepository generationRepository, VersionRepository versionRepository) throws FileNotFoundException {

        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/data.json");

           // System.out.println(file.getAbsolutePath()+", "+file.exists());
            BrandData[] brandDataList = objectMapper.readValue(file, BrandData[].class);
            //System.out.println(Arrays.toString(brandDataList));
            for(BrandData brandD: brandDataList){
               // System.out.println(brandD.name);
                Brand brand = new Brand();
                brand.setName(brandD.name);
                brandRepository.save(brand);
                if (brandD.models() != null) {
                    for (ModelData modelD : brandD.models()) {
                      //  System.out.println("   " + modelD.name);
                        Model model = new Model();
                        model.setName(modelD.name);
                        model.setBrand(brand);
                        modelRepository.save(model);
                        if (modelD.generations() != null) {
                            for (GenerationData generationD : modelD.generations()) {
                        //        System.out.println("      " + generationD.name);
                                Generation generation = new Generation();
                                generation.setName(generationD.name);
                                generation.setModel(model);
                                generation.setEndYear(generationD.endYear);
                                generation.setStartYear(generationD.startYear);
                                generationRepository.save(generation);
                                if (generationD.versions() != null) {
                                    for (VersionData versionD : generationD.versions()) {
                                     //   System.out.println("         " + versionD.name);
                                        Version version = new Version();
                                        version.setStartYear(versionD.startYear);
                                        version.setName(versionD.name);
                                        version.setGeneration(generation);
                                        version.setEndYear(versionD.endYear);
                                        versionRepository.save(version);
                                    }
                                }
                            }
                        }
                        if (modelD.versions() != null) {
                            for (VersionData versionD : modelD.versions()) {
                              //  System.out.println("         " + versionD.name);
                                Version version = new Version();
                                version.setStartYear(versionD.startYear);
                                version.setName(versionD.name);
                                version.setModel(model);
                                version.setEndYear(versionD.endYear);
                                versionRepository.save(version);
                            }
                        }
                    }
                }
            }
         };




    }

    record BrandData(String name, List<ModelData> models) {}
    record ModelData(String name, List<GenerationData> generations, List<VersionData> versions) {}
    record GenerationData(String name, String startYear, String endYear, List<VersionData> versions) {}
    record VersionData(String name, String startYear, String endYear) {}
}

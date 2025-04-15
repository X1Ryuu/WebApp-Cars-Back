package com.example.demo;

import com.example.demo.cars.model.archive.Brand;
import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.model.archive.Model;
import com.example.demo.cars.model.archive.Version;
import com.example.demo.cars.repository.BrandRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class Initializr implements CommandLineRunner {
    private BrandRepository brandRepository;
    Initializr(BrandRepository brandRepository) {this.brandRepository = brandRepository;}

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("data.json").getInputStream();

        TypeReference<List<Brand>> typeRef = new TypeReference<>() {};
        List<Brand> brands = mapper.readValue(inputStream, typeRef);

        for (Brand brandDto : brands) {
            Brand brand = new Brand();
            brand.setName(brandDto.getName());
            System.out.println(brand.getName());
            List<Model> modelList = new ArrayList<>();

            if (brandDto.getModels() != null) {
                for (Model modelDto : brandDto.getModels()) {
                    Model model = new Model();
                    model.setName(modelDto.getName());
                    model.setBrand(brand);
                    System.out.println("   "+model.getName());
                    List<Generation> generations = new ArrayList<>();
                    if (modelDto.getGenerations() != null) {
                        for (Generation genDto : modelDto.getGenerations()) {
                            Generation gen = new Generation();
                            gen.setName(genDto.getName());
                            gen.setStartYear(genDto.getStartYear());
                            gen.setEndYear(genDto.getEndYear());
                            gen.setModel(model);
                            System.out.println("      "+gen.getName());
                            List<Version> versions = new ArrayList<>();
                            if (genDto.getVersions() != null) {
                                for (Version verDto : genDto.getVersions()) {
                                    Version version = new Version();
                                    version.setName(verDto.getName());
                                    version.setGeneration(gen);
                                    version.setEndYear(verDto.getEndYear());
                                    version.setStartYear(verDto.getStartYear());
                                    System.out.println("         "+version.getName());
                                    versions.add(version);
                                }
                                gen.setVersions(versions);
                            }

                            generations.add(gen);
                        }
                        model.setGenerations(generations);
                    }else if(modelDto.getVersions() != null) {
                        List<Version> versions = new ArrayList<>();
                        for (Version verDto : modelDto.getVersions()) {
                            Version version = new Version();
                            version.setName(verDto.getName());
                            version.setModel(model);
                            version.setEndYear(verDto.getEndYear());
                            version.setStartYear(verDto.getStartYear());
                            System.out.println("         " + version.getName());
                            versions.add(version);
                        }
                        model.setVersions(versions);
                    }
                    modelList.add(model);
                }

            }

            brand.setModels(modelList);
            brandRepository.save(brand);
        }
    }
}

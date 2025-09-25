package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.repository.GenerationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationService {

    private GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository){this.generationRepository = generationRepository;}

    public List<Generation> findGenerationsByModelName(String name){return generationRepository.findAllByModel_NameOrderByName(name);}
    public List<Generation> findGenerationsByModelId(Long id){return generationRepository.findGenerationsByModel_IdOrderByName(id);}
}

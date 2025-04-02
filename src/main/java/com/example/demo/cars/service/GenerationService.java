package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.repository.GenerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationService {


    GenerationRepository generationRepository;
    GenerationService(GenerationRepository generationRepository){
        this.generationRepository=generationRepository;
    }

    public List<Generation> findGenerationsByModelId(Long id){
        return generationRepository.findAllByModel_Id(id);
    }

    public List<Generation> findGenerationsByModelName(String name){
        return generationRepository.findAllByModel_Name(name);
    }

    public Generation findByName(String name){return generationRepository.findByName(name);}
}

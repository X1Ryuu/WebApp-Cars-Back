package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Engine;
import com.example.demo.cars.repository.EngineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {
    EngineRepository engineRepository;
    public EngineService(EngineRepository engineRepository){this.engineRepository = engineRepository;}

    public List<Engine> findEnginesByGeneration(Long id){
        return engineRepository.findEnginesByGeneration_IdOrderByName(id);
    }

    public List<Engine> findEnginesByModel(Long id){
        return engineRepository.findEnginesByModel_IdOrderByName(id);
    }
    public List<Engine> findEnginesByVersion(Long id){
        return engineRepository.findEnginesByVersion_IdOrderByName(id);
    }


}

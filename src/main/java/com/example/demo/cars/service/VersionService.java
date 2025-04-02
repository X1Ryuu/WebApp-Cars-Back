package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Generation;
import com.example.demo.cars.model.archive.Version;
import com.example.demo.cars.repository.ModelRepository;
import com.example.demo.cars.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VersionService {

    VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void addVersion(Version version) {
        versionRepository.save(version);
    }


    public List<Version> getVersionsByModelId(Long id){return versionRepository.findAllByModel_Id(id);}
    public List<Version> getVersionsByGenerationId(Long id){return versionRepository.findAllByGeneration_Id(id);}

    public List<Version> getVersionsByModelName(String name){return versionRepository.findAllByModel_Name(name);}
    public List<Version> getVersionsByGenerationName(String name){return versionRepository.findAllByGeneration_Name(name);}
}

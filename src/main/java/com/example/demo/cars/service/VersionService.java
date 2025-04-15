package com.example.demo.cars.service;

import com.example.demo.cars.model.archive.Version;
import com.example.demo.cars.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {

    @Autowired
    VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void addVersion(Version version) {
        versionRepository.save(version);
    }
    public List<Version> findAllVersions(){return versionRepository.findAll();}

    public List<Version> getVersionsByModelName(String name) {
        return versionRepository.findAllByModel_NameOrderByName(name);
    }

    public List<Version> getVersionsByGenerationName(String name) {
        return versionRepository.findAllByGeneration_NameOrderByName(name);
    }
}

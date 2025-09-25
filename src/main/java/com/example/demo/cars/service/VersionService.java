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

    public List<Version> findVersionsByModelName(String name) {
        return versionRepository.findAllByModel_NameOrderByName(name);
    }

    public List<Version> findVersionsByGenerationName(String name) {
        return versionRepository.findAllByGeneration_NameOrderByName(name);
    }

    public List<Version> findVersionsByModelId(Long id) {
        return versionRepository.findVersionsByModel_IdOrderByName(id);
    }

    public List<Version> findVersionsByGenerationId(Long id) {
        return versionRepository.findVersionsByGeneration_IdOrderByName(id);
    }
}

package com.example.demo.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EngineDTO {
    private String name;
    private String versionName;
    private String generationName;
    private String modelName;
    private String gasolineType;
    private Double volume;
    private Integer hp;
    private Integer hybridHp;
    private String injection;
    private String fromDate;
}

package com.example.demo.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VersionDTO {
    private String name;
    private String modelName;
    private String generationName;
    private String startYear;
    private String endYear;
}

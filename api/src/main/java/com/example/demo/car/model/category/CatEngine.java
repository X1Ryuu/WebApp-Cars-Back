package com.example.demo.car.model.category;

import com.example.demo.car.model.archive.Model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CEngines")
public class CatEngine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private String gasoline;
    private int volume;
    private int hp;
    private float acceleration;
    private String driveType;
    private String gearbox;
    private Long cost;

    @ManyToMany(mappedBy = "engine")
    @JsonManagedReference
    private List<CatOption> options;
}

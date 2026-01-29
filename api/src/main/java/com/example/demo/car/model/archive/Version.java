package com.example.demo.car.model.archive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Versions")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    private String startYear;
    private String endYear;


    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference("model-version")
    @ToString.Exclude
    private Model model;


    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference("generation-version")
    @ToString.Exclude
    private Generation generation;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL)
    @JsonManagedReference("version-engine")
    private List<Engine> engines;



}

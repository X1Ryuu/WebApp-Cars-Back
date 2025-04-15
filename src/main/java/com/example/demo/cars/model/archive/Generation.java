package com.example.demo.cars.model.archive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Generations")
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference
    private Model model;

    private String startYear;
    private String endYear;

    @OneToMany(mappedBy = "generation", cascade = CascadeType.ALL)
    @JsonManagedReference("generation-version")
    private List<Version> versions;

}

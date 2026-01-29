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
@ToString(exclude = "brand")
@Table(name = "Models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference("brand-model")
    private Brand brand;




    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    @JsonManagedReference("model-version")
    private List<Version> versions;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    @JsonManagedReference("model-generation")
    private List<Generation> generations;

    @OneToMany(mappedBy = "model")
    @JsonManagedReference("model-engine")
    private List<Engine> engines;


}



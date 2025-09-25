package com.example.demo.cars.model.archive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "version_id")
    @JsonBackReference("version-engine")
    private Version version;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference("generation-engine")
    private Generation generation;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference("model-engine")
    private Model model;

    private String gasoline;
    private String volume;
    private Integer hp;
    private Integer hybridHp;
    private String injection;
    private String fromDate;


}

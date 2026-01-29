package com.example.demo.car.model.archive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "version_id")
    @JsonBackReference("version-engine")
    @ToString.Exclude
    private Version version;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference("generation-engine")
    @ToString.Exclude
    private Generation generation;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference("model-engine")
    @ToString.Exclude
    private Model model;

    private String gasoline;
    private String volume;
    private Integer hp;
    private Integer hybridHp;
    private String injection;
    private String fromDate;


}

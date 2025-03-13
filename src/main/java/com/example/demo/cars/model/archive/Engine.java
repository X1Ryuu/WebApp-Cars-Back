package com.example.demo.cars.model.archive;

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
    private Version version;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

    private String gasoline;
    private int volume;
    private int hp;
    private int kWp;
    private String injection;
    private int fromDate;
/*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_description_id", referencedColumnName = "id")
    private EngineDescription engineDescription;*/

}

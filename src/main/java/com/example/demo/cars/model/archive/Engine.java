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
    @JsonBackReference
    private Version version;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference
    private Generation generation;

    private String gasoline;
    private int volume;
    private int hp;
    private int kWp;
    private String injection;
    private int fromDate;


}

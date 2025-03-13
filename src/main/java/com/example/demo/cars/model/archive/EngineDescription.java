package com.example.demo.cars.model.archive;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Descriptions")
public class EngineDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    private String gasoline;
    private int volume;
    private int hp;
    private int kWp;
    private String injection;
    private int fromDate;
    private float acceleration;
    private float topSpeed;
    private String engineType;
    private int numOfCylinders;
    private String gearbox;
    private String driveType; //AWD-constantConnection, AWD-automatic,
    // AWD-manualConnection or (xDrive, 4-matic, quattro), FWD, RWD

}

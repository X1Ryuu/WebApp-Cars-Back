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
@Table(name = "Descriptions")
public class EngineDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "engine_id")
    @JsonBackReference("engine-description")
    @ToString.Exclude
    private Engine engine;

    private String gasoline;
    private Integer volume;
    private Integer hp;
    private Integer kWp;
    private String injection;
    private Integer fromDate;
    private Float acceleration;
    private Float topSpeed;
    private String engineType;
    private Integer numOfCylinders;
    private String gearbox;
    private String driveType; //AWD-constantConnection, AWD-automatic,
    // AWD-manualConnection or (xDrive, 4-matic, quattro), FWD, RWD

}

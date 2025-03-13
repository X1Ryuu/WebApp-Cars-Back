package com.example.demo.cars.model.archive;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
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
    private Model model;


    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

/*    @OneToMany(mappedBy = "version")
    private List<Engine> engines;*/


    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelId=" + (model != null ? model.getId() : "null") + // 👈 Unikamy model.toString()
                '}';
    }
}

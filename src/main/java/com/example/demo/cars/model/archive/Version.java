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
    private Model model;


    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference("generation-version")
    private Generation generation;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL)
    @JsonManagedReference("version-engine")
    private List<Engine> engines;


    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelId=" + (model != null ? model.getId() : "null") + // 👈 Unikamy model.toString()
                '}';
    }
}

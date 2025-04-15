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

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", brandId=" + (brand != null ? brand.getId() : "null") +
/*                ", versionsSize=" + (versions != null ? versions.size() : 0) + // 👈 Unikamy wersji w toString()
                ", generationsSize=" + (generations != null ? generations.size() : 0)+*/
                '}';
    }
}



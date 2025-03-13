package com.example.demo.cars.model.archive;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;

/*    private String nameId;*/
    private String name;
    private String logo;

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
    //            ", nameId='" + nameId + '\'' +
                ", modelsSize=" + (models != null ? models.size() : 0) + // 👈 Nie wywołujemy models.toString()
                '}';
    }

}

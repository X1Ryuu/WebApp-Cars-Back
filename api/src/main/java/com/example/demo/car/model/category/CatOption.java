package com.example.demo.car.model.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "COptions")
public class CatOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int cost;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "cat-option_cat-engine", // nazwa tabeli pośredniczącej
            joinColumns = @JoinColumn(name = "coption_id"), // kolumna wskazująca na tę encję
            inverseJoinColumns = @JoinColumn(name = "cengine_id") // kolumna wskazująca na encję CatEngine
    )
    private List<CatEngine> engine;
}

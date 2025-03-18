package com.example.demo.cars.model.sale;

import com.example.demo.cars.model.archive.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Sale_Items")
public class SItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brandName;
    private String modelName;
    private Long price;
    private int power;
    private String driveType;
    private String transmission;
    private String gasoline;
    private Long distance;
    private String chassisType;

    @OneToOne(mappedBy = "item")
    private ItemDesc desc;
}

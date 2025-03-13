package com.example.demo.comparators;

import com.example.demo.cars.model.archive.Model;

public class ModelComparator implements java.util.Comparator<Model> {
    @Override
    public int compare(Model a, Model b) {
        return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
    }
}

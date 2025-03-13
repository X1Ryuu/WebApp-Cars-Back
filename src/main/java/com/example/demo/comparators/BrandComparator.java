package com.example.demo.comparators;

import com.example.demo.cars.model.archive.Brand;

public class BrandComparator implements java.util.Comparator<Brand> {
    @Override
    public int compare(Brand a, Brand b) {
        return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
    }
}


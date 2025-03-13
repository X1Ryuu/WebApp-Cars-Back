package com.example.demo.comparators;

import com.example.demo.cars.model.archive.Version;

public class VersionComparator implements java.util.Comparator<Version> {
    @Override
    public int compare(Version a, Version b) {
        return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
    }
}

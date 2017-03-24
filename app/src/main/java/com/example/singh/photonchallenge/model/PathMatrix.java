package com.example.singh.photonchallenge.model;

import java.util.ArrayList;
import java.util.List;

public class PathMatrix {


    private List<List<Integer>> matrix = new ArrayList<List<Integer>>();

    public void addColumn(List<Integer> listItem) {
        matrix.add(listItem);
    }

    public int getWidth() {
        return matrix.size();
    }

    public int getHeight() {
        return matrix.get(0).size();
    }

    public List<Integer> getColumn(int i) {
        return matrix.get(i);
    }


    public int getValue(int x, int y) {


        return matrix.get(x).get(y);

    }
}

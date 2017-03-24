package com.example.singh.photonchallenge.solution;

import com.example.singh.photonchallenge.model.PathMatrix;
import com.example.singh.photonchallenge.model.Step;

import java.util.ArrayList;


public class ShortestPath extends ArrayList<Step> {
    private PathMatrix pathMatrix;

    public ShortestPath(PathMatrix pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    @Override
    public ShortestPath clone() {
        ShortestPath shortestPath = new ShortestPath(pathMatrix);

        for (Step step : this) {

            shortestPath.add(step.clone());

        }
        return shortestPath;
    }

    public int getCost() {

        int accumulator = 0;
        for (Step step : this) {
            accumulator += step.getCost();
        }

        return accumulator;
    }

    public boolean terminates() {
        return this.size() == pathMatrix.getWidth();
    }


}

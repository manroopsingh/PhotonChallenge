package com.example.singh.photonchallenge.solution;

import com.example.singh.photonchallenge.model.PathMatrix;
import com.example.singh.photonchallenge.model.Step;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathFinder {

    private PathMatrix pathMatrix;

    public PathFinder(PathMatrix pathMatrix) {
        this.pathMatrix = pathMatrix;
    }

    public ShortestPath navigate() {

        List<ShortestPath> listOfPaths = new ArrayList<ShortestPath>();

        for (int i = 0; i < pathMatrix.getHeight(); i++) {
            listOfPaths.add(iterate(new ShortestPath(pathMatrix), 0, i));
        }

        return getBestPath(listOfPaths);

    }


    private ShortestPath iterate(ShortestPath path, int x, int y) {
        if (terminates(path))
            return path;

        int test = path.getCost() + pathMatrix.getValue(x, y);
        if (test > 50)
            return path;

        Step step = new Step();
        step.setRow(y + 1);
        step.setCost(pathMatrix.getValue(x, y));
        path.add(step);

        if (terminates(path))
            return path;


        List<ShortestPath> listOfPaths = new ArrayList<ShortestPath>();

        for (int i = -1; i < 2; i++) {
            listOfPaths.add(iterate(path.clone(), x + 1, normalizeIndex(y + i)));
        }

        return getBestPath(listOfPaths);
    }

    public boolean terminates(ShortestPath shortestPath) {
        return shortestPath.size() == pathMatrix.getWidth();
    }


    private int normalizeIndex(int test) {
        if (test < 0)
            return pathMatrix.getHeight() - 1;
        if (test > pathMatrix.getHeight() - 1)
            return 0;
        return test;
    }

    private ShortestPath getBestPath(List<ShortestPath> shortestPathList) {
        if (shortestPathList != null && shortestPathList.size() > 0) {
            filterByLength(shortestPathList);
            filterByCost(shortestPathList);
            return shortestPathList.get(0);
        }

        return null;
    }

    private List<ShortestPath> filterByLength(List<ShortestPath> shortestPathList) {
        int longest = 0;

        for (ShortestPath shortestPath : shortestPathList) {
            if (shortestPath.size() > longest)
                longest = shortestPath.size();
        }

        Iterator it = shortestPathList.iterator();

        while (it.hasNext()) {
            if (((ShortestPath) it.next()).size() < longest)
                it.remove();
        }

        return shortestPathList;
    }

    private List<ShortestPath> filterByCost(List<ShortestPath> shortestPathList) {
        int lowest = 51;

        for (ShortestPath shortestPath : shortestPathList) {
            if (shortestPath.getCost() < lowest)
                lowest = shortestPath.getCost();
        }

        Iterator it = shortestPathList.iterator();

        while (it.hasNext()) {
            if (((ShortestPath) it.next()).getCost() > lowest)
                it.remove();
        }

        return shortestPathList;
    }

}

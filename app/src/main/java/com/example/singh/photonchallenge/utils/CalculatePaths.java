package com.example.singh.photonchallenge.utils;


public class CalculatePaths {


    private int matrixHeight;
    private int matrixWidth;
    private int[][] matrix;
    private String isValid;
    private String pathString, distanceString;
    private String[] paths;
    private int[] distances;

    public CalculatePaths(int[][] matrix) {
        this.matrix = matrix;
        matrixHeight = matrix.length;
        matrixWidth = matrix[0].length;
    }


    public String findShortestPath() {
        paths = new String[matrixHeight];
        distances = new int[matrixHeight];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < matrixHeight; i++) {
            route(i);
        }

        int shortestPath[] = getShortestPath(distances);

        pathString = paths[shortestPath[1]];
        distanceString = String.valueOf(distances[shortestPath[1]]);

        if (distances[shortestPath[1]] > 50) {
            isValid = "No";
            editFinalStrings(pathString);

        } else isValid = "Yes";


        result.append(isValid);
        result.append("\n");
        result.append(distanceString);
        result.append("\n");
        result.append(pathString);

        return result.toString();

    }

    private void editFinalStrings(String path) {
        int[] routes = new int[matrixWidth];
        StringBuilder sb = new StringBuilder();
        int total = 0;
        int j = 0;
        for (int i = 0; i < path.length(); i++) {
            if ((path.charAt(i) == '[') || (path.charAt(i) == ']') || (path.charAt(i) == ',')) {
                if (path.charAt(i) == ',') j = j + 1;

            } else {
                routes[j] = Character.getNumericValue(path.charAt(i));

            }

        }
        sb.append("[");
        int k;
        out:
        for (int i = 0; i < matrixWidth; i++) {
            k = routes[i];
            if ((total + matrix[k][i]) > 50) {

                break out;
            }
            total = total + matrix[k][i];

            sb.append(String.valueOf(routes[i]) + ",");

        }
        sb.append("]");
        pathString = sb.toString();
        distanceString = String.valueOf(total);

    }


    public void route(int rowIndex) {

        StringBuilder path = new StringBuilder();
        int[] route = new int[matrixWidth + 1];
        int total = matrix[rowIndex][0];
        route[0] = rowIndex;
        path.append("[" + route[0]);

        for (int widthIndex = 1; widthIndex < matrixWidth; widthIndex++) {

            int[] value = getMinValue(getColumnAt(widthIndex), route[widthIndex - 1]);

            total = total + value[0];


            route[widthIndex] = value[1];
            path.append("," + route[widthIndex]);

        }
        route[matrixWidth] = total;
        path.append("]");
        paths[rowIndex] = path.toString();
        distances[rowIndex] = route[matrixWidth];


    }

    public int[] getMinValue(int[] column, int rowIndex) {
        int[] minValue = new int[2];
        int upperRowIndex = rowIndex - 1, lowerRowIndex = rowIndex + 1;
        if (rowIndex == 0) upperRowIndex = 0;
        if (rowIndex == column.length - 1) lowerRowIndex = rowIndex;

        minValue[0] = column[upperRowIndex];
        minValue[1] = upperRowIndex;
        for (int i = upperRowIndex; i <= lowerRowIndex; i++) {
            if (column[i] < minValue[0]) {
                minValue[0] = column[i];
                minValue[1] = i;
            }
        }
        return minValue;
    }

    public int[] getColumnAt(int columnIndex) {

        int[] column = new int[matrixHeight];
        for (int row = 0; row < matrixHeight; row++) {
            column[row] = matrix[row][columnIndex];
        }
        return column;
    }

    public int[] getShortestPath(int[] pathDistances) {
        int[] minValue = new int[2];

        minValue[0] = pathDistances[0];
        minValue[1] = 0;
        for (int i = 0; i < pathDistances.length; i++) {
            if (pathDistances[i] < minValue[0]) {
                minValue[0] = pathDistances[i];
                minValue[1] = i;
            }
        }
        return minValue;
    }
}

package com.example.singh.photonchallenge;


import com.example.singh.photonchallenge.utils.CalculatePaths;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LogicUnitTest {


    //2 sample matrices for testing
    int[][] sampleMatrix1 = new int[][]{
            {4, 5, 8, 6},
            {4, 6, 6, 2},
            {7, 3, 8, 2},
            {3, 6, 2, 7}

    };
    int[][] sampleMatrix2 = new int[][]{
            {1, 8, 3, 6, 1},
            {4, 5, 1, 1, 5},
            {4, 5, 1, 5, 5},
            {7, 1, 9, 1, 2},
            {1, 1, 1, 1, 4}
    };


    CalculatePaths calculatePaths1;
    CalculatePaths calculatePaths2;
    @Before
    public void setup() {
        calculatePaths1 = new CalculatePaths(sampleMatrix1);
        calculatePaths2 = new CalculatePaths(sampleMatrix2);

    }

    //testing minimum value in a column for a given sample matrix with range rowIndex-1 to rowIndex-1
    @Test
    public void testMinValueFnx() {

        int[] sampleColumn = calculatePaths1.getColumnAt(2);
        int[] returnedValue = calculatePaths1.getMinValue(sampleColumn, 2);
        assertTrue(returnedValue[0] == 2);
        assertTrue(returnedValue[1] == 3);

    }

    //testing returned column from a given sample matrix at a given columnIndex
    @Test
    public void testGetColumnAtFxn() {


        int[] returnedColumn = calculatePaths1.getColumnAt(1);
        for (int i = 0; i < sampleMatrix1[0].length; i++) {
            assertTrue(returnedColumn[i] == sampleMatrix1[i][1]);
        }

    }

    //testing minimumn value in a array
    @Test
    public void testGetShortestPathFxn() {

        int[] pathDistances = calculatePaths2.getColumnAt(4);
        int[] returnedValue = calculatePaths2.getShortestPath(pathDistances);

        assertTrue(returnedValue[0] == 1);
        assertTrue(returnedValue[1] == 0);

    }

    //testing final result for shortest path in a given matrix
   @Test
    public void testFindShortestPathFxn(){
       StringBuilder sampleMatrix1Result = new StringBuilder();
       sampleMatrix1Result.append("Yes");
       sampleMatrix1Result.append("\n");
       sampleMatrix1Result.append("10");
       sampleMatrix1Result.append("\n");
       sampleMatrix1Result.append("[3,2,3,2]");

       StringBuilder sampleMatrix2Result = new StringBuilder();
       sampleMatrix2Result.append("Yes");
       sampleMatrix2Result.append("\n");
       sampleMatrix2Result.append("5");
       sampleMatrix2Result.append("\n");
       sampleMatrix2Result.append("[4,3,2,1,0]");

       //testing result for sample1
       String returnedResult1 = calculatePaths1.findShortestPath();
       assertTrue(returnedResult1.equals(sampleMatrix1Result.toString()));

       //testing result for sample2
       String returnedResult2 = calculatePaths2.findShortestPath();
       assertTrue(returnedResult2.equals(sampleMatrix2Result.toString()));


   }

}
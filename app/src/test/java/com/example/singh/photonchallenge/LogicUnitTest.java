package com.example.singh.photonchallenge;

import com.example.singh.photonchallenge.model.PathMatrix;
import com.example.singh.photonchallenge.model.Step;
import com.example.singh.photonchallenge.solution.PathFinder;
import com.example.singh.photonchallenge.solution.ShortestPath;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LogicUnitTest {
    @Test
    public void testTrivial() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> firstColumn = new ArrayList<Integer>();
        firstColumn.add(1);
        pathMatrix.addColumn(firstColumn);
        PathFinder pathFinder = new PathFinder(pathMatrix);
        ShortestPath derivedPath = pathFinder.navigate();
        Step testStep = new Step();
        testStep.setCost(1);
        testStep.setRow(1);
        ShortestPath testPath = new ShortestPath(pathMatrix);
        testPath.add(testStep);
        assertTrue(pathFinder.terminates(derivedPath));
        assertTrue(derivedPath.equals(testPath));
        assertTrue(derivedPath.getCost() == 1);
    }

    @Test
    public void testRow() {
        PathMatrix pathMatrix = new PathMatrix();

        for (int i = 0; i < 5; i++) {
            List<Integer> column = new ArrayList<Integer>();
            column.add(1);
            pathMatrix.addColumn(column);
        }

        PathFinder tester = new PathFinder(pathMatrix);
        ShortestPath derivedPath = tester.navigate();

        ShortestPath testPath = new ShortestPath(pathMatrix);
        for (int i = 0; i < 5; i++) {
            Step step = new Step();
            step.setRow(1);
            step.setCost(1);
            testPath.add(step);
        }


        assertTrue(derivedPath.getCost() == testPath.getCost());

        for (int i = 0; i < 5; i++) {
            assertTrue(derivedPath.get(i).getRow() == testPath.get(i).getRow());
        }

    }

    @Test
    public void testColumn() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> listStep = new ArrayList<Integer>();

        listStep.add(5);
        listStep.add(8);
        listStep.add(5);
        listStep.add(3);
        listStep.add(5);

        pathMatrix.addColumn(listStep);

        PathFinder tester = new PathFinder(pathMatrix);

        ShortestPath derivedPath = tester.navigate();

        assertTrue(derivedPath.getCost() == 3);
    }

    @Test
    public void testOverrun() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(69);
        list1.add(51);
        list1.add(60);

        pathMatrix.addColumn(list1);

        PathFinder tester = new PathFinder(pathMatrix);

        ShortestPath derivedPath = tester.navigate();

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 0);

    }

    @Test
    public void testNoPath() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(19);
        list1.add(21);
        list1.add(20);

        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(10);
        list2.add(23);
        list2.add(12);

        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(19);
        list3.add(20);
        list3.add(20);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(10);
        list4.add(19);
        list4.add(11);

        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        PathFinder tester = new PathFinder(pathMatrix);

        ShortestPath derivedPath = tester.navigate();

        System.out.println("length of derived path is " + derivedPath.getCost());

        assertTrue(derivedPath.getCost() == 48);
        assertTrue(derivedPath.size() == 3);
    }

    @Test
    public void testNegativePath() {
        PathMatrix pathMatrix = new PathMatrix();

        List<Integer> list1 = new ArrayList<Integer>();

        list1.add(6);
        list1.add(-5);
        list1.add(3);
        list1.add(9);


        List<Integer> list2 = new ArrayList<Integer>();

        list2.add(3);
        list2.add(2);
        list2.add(-2);
        list2.add(-1);


        List<Integer> list3 = new ArrayList<Integer>();

        list3.add(-5);
        list3.add(4);
        list3.add(6);
        list3.add(-2);

        List<Integer> list4 = new ArrayList<Integer>();

        list4.add(9);
        list4.add(10);
        list4.add(10);
        list4.add(10);


        pathMatrix.addColumn(list1);
        pathMatrix.addColumn(list2);
        pathMatrix.addColumn(list3);
        pathMatrix.addColumn(list4);

        PathFinder tester = new PathFinder(pathMatrix);

        ShortestPath derivedPath = tester.navigate();

        System.out.println("length of derived path is " + derivedPath.getCost());

        assertTrue(derivedPath.getCost() == 0);
        assertTrue(derivedPath.size() == 4);
        assertTrue(derivedPath.get(0).getRow() == 2);
        assertTrue(derivedPath.get(1).getRow() == 3);
        assertTrue(derivedPath.get(2).getRow() == 4);
        assertTrue(derivedPath.get(3).getRow() == 1);


    }
}
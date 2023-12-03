package com.dixit.soham.aoc.day3.gear.ratios;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    @Test
    public void exampleCase_partOne() throws IOException {
        InputStream testcaseFile = getClass().getClassLoader().getResourceAsStream("day3/example-input.txt");
        Solver solver = new Solver(testcaseFile);
        assertThat(solver.getPartOneSolution()).isEqualTo(4361);
    }

    @Test
    public void puzzleCase_partOne() throws IOException {
        InputStream testcaseFile = getClass().getClassLoader().getResourceAsStream("day3/puzzle-input.txt");
        Solver solver = new Solver(testcaseFile);
        assertThat(solver.getPartOneSolution()).isEqualTo(546312);
    }

    @Test
    public void exampleCase_partTwo() throws IOException {
        InputStream testcaseFile = getClass().getClassLoader().getResourceAsStream("day3/example-input.txt");
        Solver solver = new Solver(testcaseFile);
        assertThat(solver.getPartTwoSolution()).isEqualTo(467835);
    }

    @Test
    public void puzzleCase_partTwo() throws IOException {
        InputStream testcaseFile = getClass().getClassLoader().getResourceAsStream("day3/puzzle-input.txt");
        Solver solver = new Solver(testcaseFile);
        assertThat(solver.getPartTwoSolution()).isEqualTo(87449461);
    }

}
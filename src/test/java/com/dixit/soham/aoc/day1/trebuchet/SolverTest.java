package com.dixit.soham.aoc.day1.trebuchet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    @Test
    public void exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day1/part1/example-input.txt");
        Solver solver = new Solver(inputFileURL, false);
        assertThat(solver.getSumOfCalibrationVals()).isEqualTo(142);
    }

    @Test
    public void puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day1/part1/input.txt");
        Solver solver = new Solver(inputFileURL, false);
        assertThat(solver.getSumOfCalibrationVals()).isEqualTo(54338);
    }

    @Test
    public void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day1/part2/example-input.txt");
        Solver solver = new Solver(inputFileURL, true);
        assertThat(solver.getSumOfCalibrationVals()).isEqualTo(281);
    }

    @Test
    public void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day1/part2/input.txt");
        Solver solver = new Solver(inputFileURL, true);
        assertThat(solver.getSumOfCalibrationVals()).isEqualTo(53389);
    }

}
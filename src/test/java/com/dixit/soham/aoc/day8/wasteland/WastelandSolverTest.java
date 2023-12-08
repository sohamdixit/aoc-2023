package com.dixit.soham.aoc.day8.wasteland;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class WastelandSolverTest {

    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day8/part1/example-input.txt");
        WastelandSolver wastelandSolver = new WastelandSolver(inputFileURL, false);
        wastelandSolver.solve();
        assertThat(wastelandSolver.getSteps()).isEqualTo(2L);
    }

    @Test
    void partOne_exampleCase_2() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day8/part1/example-input-2.txt");
        WastelandSolver wastelandSolver = new WastelandSolver(inputFileURL, false);
        wastelandSolver.solve();
        assertThat(wastelandSolver.getSteps()).isEqualTo(6L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day8/part1/puzzle-input.txt");
        WastelandSolver wastelandSolver = new WastelandSolver(inputFileURL, false);
        wastelandSolver.solve();
        assertThat(wastelandSolver.getSteps()).isEqualTo(22357L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day8/part2/example-input.txt");
        WastelandSolver wastelandSolver = new WastelandSolver(inputFileURL, true);
        wastelandSolver.solve();
        assertThat(wastelandSolver.getSteps()).isEqualTo(6L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day8/part2/puzzle-input.txt");
        WastelandSolver wastelandSolver = new WastelandSolver(inputFileURL, true);
        wastelandSolver.solve();
        assertThat(wastelandSolver.getSteps()).isEqualTo(10371555451871L);
    }

}
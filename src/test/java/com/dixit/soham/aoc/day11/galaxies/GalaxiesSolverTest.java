package com.dixit.soham.aoc.day11.galaxies;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class GalaxiesSolverTest {
    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day11/example-input.txt");
        GalaxiesSolver galaxiesSolver = new GalaxiesSolver(inputFileURL, 2L);
        galaxiesSolver.solve();
        assertThat(galaxiesSolver.getSumOfLengths()).isEqualTo(374L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day11/puzzle-input.txt");
        GalaxiesSolver galaxiesSolver = new GalaxiesSolver(inputFileURL, 2L);
        galaxiesSolver.solve();
        assertThat(galaxiesSolver.getSumOfLengths()).isEqualTo(10154062L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day11/example-input.txt");
        GalaxiesSolver galaxiesSolver = new GalaxiesSolver(inputFileURL, 10L);
        galaxiesSolver.solve();
        assertThat(galaxiesSolver.getSumOfLengths()).isEqualTo(1030L);
    }

    @Test
    void partTwo_exampleCase_2() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day11/example-input.txt");
        GalaxiesSolver galaxiesSolver = new GalaxiesSolver(inputFileURL, 100L);
        galaxiesSolver.solve();
        assertThat(galaxiesSolver.getSumOfLengths()).isEqualTo(8410L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day11/puzzle-input.txt");
        GalaxiesSolver galaxiesSolver = new GalaxiesSolver(inputFileURL, 1000000L);
        galaxiesSolver.solve();
        assertThat(galaxiesSolver.getSumOfLengths()).isEqualTo(553083047914L);
    }

}
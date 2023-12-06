package com.dixit.soham.aoc.day6.boats;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class BoatSolverTest {

    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day6/example-input.txt");
        BoatSolver boatSolver = new BoatSolver(inputFileURL, false);
        boatSolver.solve();
        assertThat(boatSolver.calculateNumberOfWaysToBeat()).isEqualTo(288L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day6/puzzle-input.txt");
        BoatSolver boatSolver = new BoatSolver(inputFileURL, false);
        boatSolver.solve();
        assertThat(boatSolver.calculateNumberOfWaysToBeat()).isEqualTo(3316275L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day6/example-input.txt");
        BoatSolver boatSolver = new BoatSolver(inputFileURL, true);
        boatSolver.solve();
        assertThat(boatSolver.calculateNumberOfWaysToBeat()).isEqualTo(71503L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day6/puzzle-input.txt");
        BoatSolver boatSolver = new BoatSolver(inputFileURL, true);
        boatSolver.solve();
        assertThat(boatSolver.calculateNumberOfWaysToBeat()).isEqualTo(27102791L);
    }

}
package com.dixit.soham.aoc.day9.mirage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class MirageSolverTest {

    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day9/example-input.txt");
        MirageSolver mirageSolver = new MirageSolver(inputFileURL);
        mirageSolver.solve();
        assertThat(mirageSolver.getSumOfNextVals()).isEqualTo(114L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day9/puzzle-input.txt");
        MirageSolver mirageSolver = new MirageSolver(inputFileURL);
        mirageSolver.solve();
        assertThat(mirageSolver.getSumOfNextVals()).isEqualTo(1898776583L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day9/example-input.txt");
        MirageSolver mirageSolver = new MirageSolver(inputFileURL, true);
        mirageSolver.solve();
        assertThat(mirageSolver.getSumOfNextVals()).isEqualTo(2L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day9/puzzle-input.txt");
        MirageSolver mirageSolver = new MirageSolver(inputFileURL, true);
        mirageSolver.solve();
        assertThat(mirageSolver.getSumOfNextVals()).isEqualTo(1100L);
    }

}
package com.dixit.soham.aoc.day5.seeds;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SeedsSolverTest {
    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day5/example-input.txt");
        SeedsSolver scratchCardsSolver = new SeedsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getLowestLocationNumber()).isEqualTo(35);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day5/puzzle-input.txt");
        SeedsSolver scratchCardsSolver = new SeedsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getLowestLocationNumber()).isEqualTo(265018614L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day5/example-input.txt");
        SeedsSolver scratchCardsSolver = new SeedsSolver(inputFileURL, true);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getLowestLocationNumber()).isEqualTo(46);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day5/puzzle-input.txt");
        SeedsSolver scratchCardsSolver = new SeedsSolver(inputFileURL, true);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getLowestLocationNumber()).isEqualTo(63179500L);
    }
}
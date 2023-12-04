package com.dixit.soham.aoc.day4.scratchcards;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class ScratchCardsSolverTest {

    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day4/example-input.txt");
        ScratchCardsSolver scratchCardsSolver = new ScratchCardsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getTotalPoints()).isEqualTo(13);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day4/puzzle-input.txt");
        ScratchCardsSolver scratchCardsSolver = new ScratchCardsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getTotalPoints()).isEqualTo(25651);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day4/example-input.txt");
        ScratchCardsSolver scratchCardsSolver = new ScratchCardsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getTotalCards()).isEqualTo(30);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day4/puzzle-input.txt");
        ScratchCardsSolver scratchCardsSolver = new ScratchCardsSolver(inputFileURL);
        scratchCardsSolver.solve();
        assertThat(scratchCardsSolver.getTotalCards()).isEqualTo(19499881);
    }

}
package com.dixit.soham.aoc.day7.camelcards;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCardsSolverTest {

    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day7/example-input.txt");
        CamelCardsSolver camelCardsSolver = new CamelCardsSolver(inputFileURL, false);
        camelCardsSolver.solve();
        assertThat(camelCardsSolver.getTotalWinnings()).isEqualTo(6440L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day7/puzzle-input.txt");
        CamelCardsSolver camelCardsSolver = new CamelCardsSolver(inputFileURL, false);
        camelCardsSolver.solve();
        assertThat(camelCardsSolver.getTotalWinnings()).isEqualTo(249390788L);
    }

    @Test
    void partTwo_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day7/example-input.txt");
        CamelCardsSolver camelCardsSolver = new CamelCardsSolver(inputFileURL, true);
        camelCardsSolver.solve();
        assertThat(camelCardsSolver.getTotalWinnings()).isEqualTo(5905L);
    }

    @Test
    void partTwo_testCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day7/test-input-1.txt");
        CamelCardsSolver camelCardsSolver = new CamelCardsSolver(inputFileURL, true);
        camelCardsSolver.solve();
        assertThat(camelCardsSolver.getTotalWinnings()).isEqualTo(6839L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day7/puzzle-input.txt");
        CamelCardsSolver camelCardsSolver = new CamelCardsSolver(inputFileURL, true);
        camelCardsSolver.solve();
        assertThat(camelCardsSolver.getTotalWinnings()).isEqualTo(248750248L);
    }
}
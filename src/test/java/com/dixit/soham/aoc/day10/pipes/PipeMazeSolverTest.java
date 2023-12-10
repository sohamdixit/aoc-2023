package com.dixit.soham.aoc.day10.pipes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class PipeMazeSolverTest {
    @Test
    void partOne_exampleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/example-input.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getStepsToFurthestPoint()).isEqualTo(4L);
    }

    @Test
    void partOne_exampleCase_2() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/example-input-2.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getStepsToFurthestPoint()).isEqualTo(8L);
    }

    @Test
    void partOne_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/puzzle-input.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getStepsToFurthestPoint()).isEqualTo(6725L);
    }

    @Test
    void partTwo_exampleCase_1() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/example-input.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getInteriorPoints()).isEqualTo(1L);
    }

    @Test
    void partTwo_exampleCase_3() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/example-input-3.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getInteriorPoints()).isEqualTo(8L);
    }

    @Test
    void partTwo_exampleCase_4() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/example-input-4.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getInteriorPoints()).isEqualTo(10L);
    }

    @Test
    void partTwo_puzzleCase() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("day10/puzzle-input.txt");
        PipeMazeSolver pipeMazeSolver = new PipeMazeSolver(inputFileURL);
        pipeMazeSolver.solve();
        assertThat(pipeMazeSolver.getInteriorPoints()).isEqualTo(383L);
    }

}
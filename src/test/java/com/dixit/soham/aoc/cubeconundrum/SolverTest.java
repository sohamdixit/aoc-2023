package com.dixit.soham.aoc.cubeconundrum;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    @Test
    public void solution() throws IOException {
        InputStream inputFileURL = getClass().getClassLoader().getResourceAsStream("input.txt");
        Solver solver = new Solver(inputFileURL);
        assertThat(solver.solveForPartOne(12, 13, 14)).isEqualTo(2061);
        assertThat(solver.solveForPartTwo()).isEqualTo(72596);
    }

}
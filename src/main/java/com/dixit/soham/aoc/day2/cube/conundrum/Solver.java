package com.dixit.soham.aoc.day2.cube.conundrum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private final List<Game> games = new ArrayList<>();

    public Solver(InputStream inputStream) throws IOException {
        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = reader.readLine();

        while (line != null) {
            games.add(Game.from(line));
            // read next line
            line = reader.readLine();
        }

        reader.close();

    }

    public int solveForPartOne(int red, int green, int blue) {
        return games.stream()
                .filter(game -> game.isPossibleWith(red, green, blue))
                .map(Game::getId)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Unable to calculate sum of ids"));
    }

    public int solveForPartTwo() {
        return games.stream()
                .map(Game::getPower)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Unable to calculate sum of powers"));
    }
}
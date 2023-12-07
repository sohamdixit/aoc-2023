package com.dixit.soham.aoc.day7.camelcards;


import com.dixit.soham.aoc.common.Solver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CamelCardsSolver extends Solver {

    private final boolean hasJokers;
    private Game game;

    public CamelCardsSolver(InputStream inputStream, boolean hasJokers) {
        super(inputStream);
        this.hasJokers = hasJokers;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        List<Hand> hands = new ArrayList<>();

        while (line != null) {
            hands.add(Hand.from(line, hasJokers));
            // read next line
            line = reader.readLine();
        }

        this.game = new Game(hands);
    }

    public long getTotalWinnings() {
        return game.getTotalWinnings();
    }
}

package com.dixit.soham.aoc.day7.camelcards;

import java.util.Comparator;
import java.util.List;

public class Game {

    private List<Hand> hands;

    public Game(List<Hand> hands) {
        this.hands = hands;
    }

    public long getTotalWinnings() {
        long totalWinnings = 0;
        hands.sort(Comparator.naturalOrder());
        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += hands.get(i).getBid() * (i + 1);
        }
        return totalWinnings;
    }
}

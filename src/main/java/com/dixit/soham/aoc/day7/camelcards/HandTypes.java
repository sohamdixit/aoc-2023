package com.dixit.soham.aoc.day7.camelcards;

public class HandTypes {
    enum Type {

        FIVE_OF_A_KIND(7),
        FOUR_OF_A_KIND(6),
        FULL_HOUSE(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        ONE_PAIR(2),
        HIGH_CARD(1);

        final int rank; // Higher is better

        Type(int rank) {
            this.rank = rank;
        }
    }
}

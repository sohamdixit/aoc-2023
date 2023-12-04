package com.dixit.soham.aoc.day4.scratchcards;

import com.dixit.soham.aoc.common.Solver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ScratchCardsSolver extends Solver {

    private int totalPoints;
    private int totalCards;

    public ScratchCardsSolver(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        List<Integer> cardPoints = new ArrayList<>();

        while (line != null) {
            Card card = Card.from(line);
            cardPoints.add(card.getMatchingNumbers());
            this.totalPoints += card.getPoints();
            // read next line
            line = reader.readLine();
        }

        // PART 2
        totalCards = findTotalCards(cardPoints);

        reader.close();
    }

    private int findTotalCards(List<Integer> cardPoints) {

        // Initialize number of cards to 1
        List<Integer> cards = new ArrayList<>(cardPoints.size());
        for (int i = 0; i < cardPoints.size(); i++) {
            cards.add(1);
        }

        // For each card, increment the card count of the new cards you won
        for (int i = 0; i < cardPoints.size(); i++) {
            int points = cardPoints.get(i);
            for (int j = i + 1; j <= Math.min(i + points, cards.size() - 1); j++) {
                cards.set(j, cards.get(j) + cards.get(i));
            }
        }
        return cards.stream().reduce(Integer::sum).orElseThrow();
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public int getTotalCards() {
        return this.totalCards;
    }
}

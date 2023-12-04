package com.dixit.soham.aoc.day4.scratchcards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {

    public static final Pattern CARD_REGEX = Pattern.compile("Card +(\\d+):(( +\\d+)+) \\|(( +\\d+)+)");

    private final int points;
    private final int matchingNumbers;

    List<Integer> winningNumbers;

    Set<Integer> candidateNumbers;
    public static Card from(String line) {

        List<Integer> winning;
        Set<Integer> candidates;
        Matcher matcher = CARD_REGEX.matcher(line);

        if (matcher.find()) {
            winning = toList(matcher.group(2));
            candidates = new HashSet<>(toList(matcher.group(4)));
        } else throw new RuntimeException(String.format("Unable to parse line [%s] as a valid Card.", line));

        return new Card(winning, candidates);
    }

    public int getPoints() {
        return points;
    }

    public int getMatchingNumbers() {
        return this.matchingNumbers;
    }

    private static List<Integer> toList(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
    }

    private Card(List<Integer> winningNumbers, Set<Integer> candidateNumbers) {
        this.winningNumbers = winningNumbers;
        this.candidateNumbers = candidateNumbers;
        this.matchingNumbers = calculateMatchingNumbers();
        this.points = matchingNumbers > 0 ? (int) Math.pow(2, matchingNumbers - 1) : 0;
    }

    private int calculateMatchingNumbers() {
        int winCount = 0;
        for (Integer w : winningNumbers) {
            if (candidateNumbers.contains(w)) {
                winCount++;
            }
        }
        return winCount;
    }
}

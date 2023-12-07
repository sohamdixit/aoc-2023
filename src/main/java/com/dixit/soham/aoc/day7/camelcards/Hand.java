package com.dixit.soham.aoc.day7.camelcards;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class Hand implements Comparable<Hand> {

    private static final Pattern HAND_REGEX = Pattern.compile("([A-Z0-9]+) ([0-9]+)");
    private static final Map<Character, Integer> CARD_VALS = Map.ofEntries(
            entry('A', 13),
            entry('K', 12),
            entry('Q', 11),
            entry('J', 10),
            entry('T', 9),
            entry('9', 8),
            entry('8', 7),
            entry('7', 6),
            entry('6', 5),
            entry('5', 4),
            entry('4', 3),
            entry('3', 2),
            entry('2', 1)
    );

    private static final Map<Character, Integer> CARD_VALS_2 = Map.ofEntries(
            entry('A', 12),
            entry('K', 11),
            entry('Q', 10),
            entry('T', 9),
            entry('9', 8),
            entry('8', 7),
            entry('7', 6),
            entry('6', 5),
            entry('5', 4),
            entry('4', 3),
            entry('3', 2),
            entry('2', 1),
            entry('J', 0)
    );

    private Map<Character, Integer> activeCardVals;
    private String hand;
    private Long bid;
    private Map<String, Long> cards;
    private HandTypes.Type handType;

    public Long getBid() {
        return bid;
    }

    public static Hand from(String line, boolean hasJokers) {
        Matcher matcher = HAND_REGEX.matcher(line);
        if (matcher.matches()) {
            String hand = matcher.group(1);
            Long bid = Long.parseLong(matcher.group(2));
            return new Hand(hand, bid, hasJokers);
        } else throw new RuntimeException(String.format("Unable to parse %s as a valid Hand.", line));
    }

    @Override
    public int compareTo(Hand o) {
        if (o.handType.rank != this.handType.rank) {
            return this.handType.rank - o.handType.rank;
        } else {
            // Same type of hand. Break tie by looking at each card in order
            for (int i = 0; i < o.hand.length(); i++) {
                int c = compareCards(this.hand.charAt(i), o.hand.charAt(i));
                if (c != 0) return c;
            }
        }
        return 0;
    }

    private int compareCards(char c1, char c2) {
        return activeCardVals.get(c1) - activeCardVals.get(c2);
    }

    private Hand(String hand, Long bid, boolean hasJokers) {
        this.hand = hand;
        this.bid = bid;
        this.activeCardVals = CARD_VALS;
        Map<String, Long> cards = getCardsMap(hand);
        if (hasJokers) {
            this.activeCardVals = CARD_VALS_2;
            cards = replaceJokersWithBestCard(cards);
        }
        this.cards = cards;
        this.handType = findHandType(this.cards);
    }

    private Map<String, Long> replaceJokersWithBestCard(Map<String, Long> cards) {
        if (!cards.containsKey("J")) {
            return cards;
        } else if (cards.size() == 1) {
            cards.put("A", 5L);
            cards.remove("J");
            return cards;
        } else {

            Map.Entry<String, Long> j = cards.entrySet().stream()
                    .filter(stringLongEntry -> !Objects.equals(stringLongEntry.getKey(), "J"))
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow();

            Long jokers = cards.remove("J");
            long newCount = cards.get(j.getKey()) + jokers;
            cards.put(j.getKey(), newCount);

            return cards;
        }
    }

    private LinkedHashMap<String, Long> getCardsMap(String hand) {
        return Arrays.stream(hand.split(""))
                .collect(Collectors.groupingBy(o -> o, LinkedHashMap::new, Collectors.counting()));
    }

    private HandTypes.Type findHandType(Map<String, Long> cards) {
        switch (cards.size()) {
            case 5:
                return HandTypes.Type.HIGH_CARD;
            case 4:
                return HandTypes.Type.ONE_PAIR;
            case 3:
                if (cards.containsValue(3L)) return HandTypes.Type.THREE_OF_A_KIND;
                else return HandTypes.Type.TWO_PAIR;
            case 2:
                if (cards.containsValue(4L)) return HandTypes.Type.FOUR_OF_A_KIND;
                else return HandTypes.Type.FULL_HOUSE;
            case 1:
                return HandTypes.Type.FIVE_OF_A_KIND;
            default:
                throw new RuntimeException("Invalid count - " + cards.size());
        }
    }
}

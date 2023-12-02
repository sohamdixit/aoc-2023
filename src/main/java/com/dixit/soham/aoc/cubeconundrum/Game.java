package com.dixit.soham.aoc.cubeconundrum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    public static final Pattern GAME_ID_PATTERN = Pattern.compile("Game (\\d+):");
    public static final Pattern RED_PATTERN = Pattern.compile("(\\d+) red");
    public static final Pattern GREEN_PATTERN = Pattern.compile("(\\d+) green");
    public static final Pattern BLUE_PATTERN = Pattern.compile("(\\d+) blue");
    private final int id;
    private final int maxRed;
    private final int maxGreen;
    private final int maxBlue;

    private Game(int id, int maxRed, int maxGreen, int maxBlue) {
        this.id = id;
        this.maxRed = maxRed;
        this.maxGreen = maxGreen;
        this.maxBlue = maxBlue;
    }

    public static Game from(String line) throws IOException {
        // Find the game id
        return new Game(
                getGameId(line),
                getMax(line, RED_PATTERN),
                getMax(line, GREEN_PATTERN),
                getMax(line, BLUE_PATTERN)
        );
    }

    public int getId() {
        return id;
    }

    public int getMaxRed() {
        return maxRed;
    }

    public int getMaxGreen() {
        return maxGreen;
    }

    public int getMaxBlue() {
        return maxBlue;
    }

    private static int getGameId(String line) throws IOException {
        int gameId;
        Matcher matcher = GAME_ID_PATTERN.matcher(line);
        if (matcher.find()) {
            gameId = Integer.parseInt(matcher.group(1));
        } else {
            throw new IOException("Unable to parse game id in line : " + line);
        }
        return gameId;
    }

    private static int getMax(String line, Pattern colorPattern) throws IOException {
        List<Integer> allMatchesForColor = new ArrayList<>();
        Matcher m = colorPattern.matcher(line);
        while ((m.find())) {
            allMatchesForColor.add(Integer.parseInt(m.group(1)));
        }

        Optional<Integer> max = allMatchesForColor.stream().max(Comparator.naturalOrder());
        if (max.isEmpty()) {
            throw new IOException("Unable to parse for pattern " + colorPattern + " in line " + line);
        }

        return max.get();
    }

    public boolean isPossibleWith(int red, int green, int blue) {
        return red >= maxRed && green >= maxGreen && blue >= maxBlue;
    }

    public Integer getPower() {
        return maxRed * maxGreen * maxBlue;
    }
}

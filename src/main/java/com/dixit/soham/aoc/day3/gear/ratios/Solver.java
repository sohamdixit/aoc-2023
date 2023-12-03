package com.dixit.soham.aoc.day3.gear.ratios;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

    public static final Pattern PART_NUMBER_PATTERN = Pattern.compile(".*?(\\d+).*?");
    private final int partSum;
    private final List<String> lines;

    private final Map<Pair<Integer, Integer>, List<Integer>> adjacentParts;

    public Solver(InputStream inputStream) throws IOException {
        BufferedReader reader;
        lines = new ArrayList<>();
        adjacentParts = new HashMap<>();

        reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = reader.readLine();

        while (line != null) {
            lines.add(line);
            // read next line
            line = reader.readLine();
        }

        int partSum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String prevLine = (i == 0) ? null : lines.get(i - 1);
            String nextLine = (i == lines.size() - 1) ? null : lines.get(i + 1);
            partSum += findSumOfValidParts(i, prevLine, lines.get(i), nextLine);
        }

        this.partSum = partSum;

        reader.close();
    }

    public int getPartOneSolution() {
        return partSum;
    }

    public int getPartTwoSolution() {
        AtomicInteger sumOfGearRatios = new AtomicInteger();
        adjacentParts.values().forEach(partsAdjToGears -> {
            if(partsAdjToGears.size() == 2) {
                int gearRatio = partsAdjToGears.stream().reduce(1, (a,b) -> a * b);
                sumOfGearRatios.addAndGet(gearRatio);
            }
        });
        return sumOfGearRatios.get();
    }

    private int findSumOfValidParts(int rowIndex, String prevLine, String currentLine, String nextLine) {
        Matcher m = PART_NUMBER_PATTERN.matcher(currentLine);
        int lineSum = 0;
        while ((m.find())) {
            int partStart = m.start(1);
            int partEnd = m.end(1);
            if (isValidPart(partStart, partEnd, currentLine, prevLine, nextLine)) {
                int partNum = Integer.parseInt(m.group(1));
                lineSum += partNum;
                checkIfAdjacentToGear(rowIndex, partNum, partStart, partEnd, currentLine, prevLine, nextLine);
            }
        }
        return lineSum;
    }

    private void checkIfAdjacentToGear(int rowIndex, int part, int partStart, int partEnd, String currentLine, String prevLine, String nextLine) {
        int lineLength = currentLine.length();

        int jStart = (partStart == 0) ? 0 : partStart - 1;
        int jEnd = (partEnd == lineLength) ? lineLength - 1 : partEnd;

        for (int j = jStart; j <= jEnd; j++) {
            if ((prevLine != null && isGear(prevLine.charAt(j)))) {
                addToAdjacencyMap(part, rowIndex - 1, j);
            } else if (isGear(currentLine.charAt(j))) {
                addToAdjacencyMap(part, rowIndex, j);
            } else if ((nextLine != null && isGear(nextLine.charAt(j)))) {
                addToAdjacencyMap(part, rowIndex + 1, j);
            }
        }
    }

    private void addToAdjacencyMap(int part, int rowIndex, int colIndex) {
        adjacentParts.compute(Pair.of(rowIndex, colIndex), (gearLocation, adjParts) -> {
            if (adjParts == null) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(part);
                return list;
            } else {
                adjParts.add(part);
                return adjParts;
            }
        });
    }

    private boolean isValidPart(int partStart, int partEnd, String currentLine, String prevLine, String nextLine) {
        int lineLength = currentLine.length();

        int iStart = (partStart == 0) ? 0 : partStart - 1;
        int iEnd = (partEnd == lineLength) ? lineLength - 1 : partEnd;

        for (int i = iStart; i <= iEnd; i++) {
            if ((prevLine != null && isSymbol(prevLine.charAt(i)))
                    || isSymbol(currentLine.charAt(i))
                    || (nextLine != null && isSymbol(nextLine.charAt(i)))) {
                return true;
            }
        }

        return false;
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }

    private boolean isGear(char c) {
        return c == '*';
    }
}

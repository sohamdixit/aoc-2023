package com.dixit.soham.aoc.day11.galaxies;

import com.dixit.soham.aoc.common.Solver;
import org.jgrapht.alg.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalaxiesSolver extends Solver {

    private List<Pair<Integer, Integer>> galaxyLocations;
    private boolean[] isEmptyRow;
    private boolean[] isEmptyCol;
    private final Long expansionFactor;

    public GalaxiesSolver(InputStream inputStream, long expansionFactor) {
        super(inputStream);
        this.galaxyLocations = new ArrayList<>();
        this.expansionFactor = expansionFactor;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        isEmptyRow = new boolean[10000];
        Arrays.fill(isEmptyRow, true);
        isEmptyCol = new boolean[line.length()];
        Arrays.fill(isEmptyCol, true);

        int i = 0;
        while (line != null) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    galaxyLocations.add(Pair.of(i, j));
                    isEmptyRow[i] = false;
                    isEmptyCol[j] = false;
                }
            }
            i++;
            // read next line
            line = reader.readLine();
        }
    }

    public Long getSumOfLengths() {
        Long sum = 0L;
        for (int i = 0; i < galaxyLocations.size(); i++) {
            for (int j = i + 1; j < galaxyLocations.size(); j++) {
                Pair<Integer, Integer> a = galaxyLocations.get(i);
                Pair<Integer, Integer> b = galaxyLocations.get(j);
                Long distance = adjustForExtraRowCols(distBetween(a, b), a, b);
                // System.out.printf("The distance between %s and %s is %s%n", a, b, distance);
                sum += distance;
            }
        }
        return sum;
    }

    private Long adjustForExtraRowCols(Long distance, Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        int startX, endX;
        if (a.getFirst() < b.getFirst()) {
            startX = a.getFirst();
            endX = b.getFirst();
        } else {
            startX = b.getFirst();
            endX = a.getFirst();
        }

        int startY, endY;
        if (a.getSecond() < b.getSecond()) {
            startY = a.getSecond();
            endY = b.getSecond();
        } else {
            startY = b.getSecond();
            endY = a.getSecond();
        }

        int emptyRowsOrColsInPath = 0;
        for (int i = startX + 1; i < endX; i++) {
            if (isEmptyRow[i]) {
                emptyRowsOrColsInPath++;
            }
        }

        for (int j = startY + 1; j < endY; j++) {
            if (isEmptyCol[j]) {
                emptyRowsOrColsInPath++;
            }
        }

        return distance + ((expansionFactor - 1) * emptyRowsOrColsInPath);
    }

    private static Long distBetween(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return (long) (Math.abs(a.getFirst() - b.getFirst()) + Math.abs(a.getSecond() - b.getSecond()));
    }
}

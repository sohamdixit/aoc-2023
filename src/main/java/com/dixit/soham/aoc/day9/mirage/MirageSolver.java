package com.dixit.soham.aoc.day9.mirage;

import com.dixit.soham.aoc.common.Solver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MirageSolver extends Solver {

    private final List<List<Long>> sequences;
    private final boolean backwards;

    public MirageSolver(InputStream inputStream) {
        this(inputStream, false);
    }

    public MirageSolver(InputStream inputStream, boolean backwards) {
        super(inputStream);
        this.sequences = new ArrayList<>();
        this.backwards = backwards;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();

        while (line != null) {
            List<Long> sequence =
                    new ArrayList<>(Arrays.stream(line.split(" "))
                            .filter(s -> !s.isBlank())
                            .map(Long::parseLong)
                            .toList());
            if (backwards) {
                Collections.reverse(sequence);
            }
            sequences.add(sequence);
            // read next line
            line = reader.readLine();
        }

    }

    public Long getSumOfNextVals() {
        long sum = 0;
        for (List<Long> sequence : sequences) {
            Long nextTerm = findNextTerm(sequence);
            sum += nextTerm;
        }
        return sum;
    }

    private Long findNextTerm(List<Long> sequence) {

        long a = sequence.get(0);
        long last = sequence.get(sequence.size() - 1);
        long d = sequence.get(1) - a;

        int n = sequence.size();
        if (a + ((n - 1) * d) == last) {
            // Base case (Arithmetic Progression)
            long nextTerm = (a + (n * d));
            return nextTerm;
        }

        List<Long> diffs = new ArrayList<>(sequence.size() - 1);
        for (int i = 1; i < sequence.size(); i++) {
            long diff = sequence.get(i) - sequence.get(i - 1);
            diffs.add(diff);
        }
        return sequence.get(sequence.size() - 1) + findNextTerm(diffs);
    }

}

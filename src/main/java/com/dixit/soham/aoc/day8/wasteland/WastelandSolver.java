package com.dixit.soham.aoc.day8.wasteland;

import com.dixit.soham.aoc.common.Solver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WastelandSolver extends Solver {

    private final Pattern LINE_PATTERN = Pattern.compile("([0-9A-Z]+) = \\(([0-9A-Z]+), ([0-9A-Z]+)\\)");
    private final boolean isPartTwo;
    private Map<String, String> leftMap;
    private Map<String, String> rightMap;
    private Long steps;
    private List<String> startNodes;

    public WastelandSolver(InputStream inputStream, boolean isPartTwo) {
        super(inputStream);
        this.leftMap = new HashMap<>();
        this.rightMap = new HashMap<>();
        this.startNodes = new ArrayList<>();
        this.isPartTwo = isPartTwo;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        String instructions = line;
        reader.readLine(); // discard blank line after instruction line
        line = reader.readLine();

        while (line != null) {
            Matcher matcher = LINE_PATTERN.matcher(line);
            if (!matcher.matches()) throw new RuntimeException("Unable to parse line " + line);
            String cur = matcher.group(1);
            if (cur.charAt(cur.length() - 1) == 'A') startNodes.add(cur);
            String left = matcher.group(2);
            leftMap.put(cur, left);
            String right = matcher.group(3);
            rightMap.put(cur, right);
            // read next line
            line = reader.readLine();
        }

        this.steps = findSteps(instructions, leftMap, rightMap);
    }

    private Long findSteps(String instructions, Map<String, String> leftMap, Map<String, String> rightMap) {

        int i = 0;
        List<Long> counts = new ArrayList<>(this.startNodes.size());

        if(!this.isPartTwo) this.startNodes = List.of("AAA");

        for (String s : this.startNodes) {
            String current = s;
            Long steps = 0L;

            while (!(current.charAt(2) == 'Z')) {
                current = switch (instructions.charAt(i)) {
                    case 'L' -> leftMap.get(current);
                    case 'R' -> rightMap.get(current);
                    default -> throw new RuntimeException("Invalid Instruction");
                };
                i = (i + 1) % instructions.length();
                steps++;
            }
            counts.add(steps);
        }
        return lcm(counts);
    }

    /**
     * Calculates the Lowest Common Multiple of n numbers
     * @param numbers The list of numbers
     * @return The lowest common multiple (LCM) of all numbers in the list
     */
    private static Long lcm(List<Long> numbers) {
        // Initialize result
        Long ans = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            ans = (((numbers.get(i) * ans)) /
                    (gcd(numbers.get(i), ans)));
        }

        return ans;
    }

    /**
     * Implements Euclid's GCD Algorithm
     * @param num1 first number
     * @param num2 second number
     * @return Greatest Common Divisor of num1 and num2
     */
    private static long gcd(long num1, long num2) {
        if (num1 == 0 || num2 == 0) {
            return num1 + num2;
        } else {
            long absNumber1 = Math.abs(num1);
            long absNumber2 = Math.abs(num2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }

    public long getSteps() {
        return this.steps;
    }
}

package com.dixit.soham.aoc.day6.boats;

import com.dixit.soham.aoc.common.Solver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BoatSolver extends Solver {

    private final Pattern TIMES_REGEX = Pattern.compile("Time:((\\s+\\d+)+)");
    private final Pattern DISTANCES_REGEX = Pattern.compile("Distance:((\\s+\\d+)+)");
    private final boolean isPartTwo;

    private List<Race> allRaces;

    public BoatSolver(InputStream inputStream, boolean isPartTwo) {
        super(inputStream);
        this.isPartTwo = isPartTwo;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        List<Long> times = new ArrayList<>();
        List<Long> distances = new ArrayList<>();

        while (line != null) {
            Matcher timesMatcher = TIMES_REGEX.matcher(line);
            if (timesMatcher.matches()) {
                times = readList(timesMatcher, isPartTwo);
            }
            Matcher distanceMatcher = DISTANCES_REGEX.matcher(line);
            if (distanceMatcher.matches()) {
                distances = readList(distanceMatcher, isPartTwo);
            }
            // read next line
            line = reader.readLine();
        }

        this.allRaces = new ArrayList<>();

        for (int i = 0; i < distances.size(); i++) {
            allRaces.add(new Race(times.get(i), distances.get(i)));
        }

        reader.close();
    }

    public Long calculateNumberOfWaysToBeat() {
        long total = 1;
        for(Race r : allRaces) {
            System.out.println(r);
            long numberOfWaysToBeat = numberOfWaysToBeat(r);
            total *= numberOfWaysToBeat;
            System.out.printf("%s ways to beat%n%n", numberOfWaysToBeat);
        }
        return total;
    }

    private long numberOfWaysToBeat(Race r) {
        double determinant = calcD(r.time, r.recordDistance);
        if(determinant < 0) {
            System.out.println("No ways to beat race: " + r);
            return 0;
        } else {
            double root1 = (r.time - determinant)/2.0;
            double root2 = (r.time + determinant)/2.0;
            int toExclude = 0;
            if(root1 % 1 == 0) toExclude++;
            if(root2 % 1 == 0) toExclude++;
            System.out.printf("D is %s. Roots are [%s, %s]%n", determinant, root1, root2);
            double waysToWin = (Math.floor(root2) - Math.ceil(root1)) + 1;
            return (long) (waysToWin - toExclude);
        }
    }

    private double calcD(long time, long recordDistance) {
        double tSquared = Math.pow(time, 2);
        double fourDr = 4 * recordDistance;
        return Math.sqrt(tSquared - fourDr);
    }

    private static List<Long> readList(Matcher timesMatcher, boolean isPartTwo) {
        Stream<String> stringStream = Arrays.stream(timesMatcher.group(1)
                        .split("\\s+"))
                .filter(s -> !s.isBlank());
        if(isPartTwo) {
            return List.of(Long.parseLong(stringStream.reduce((s, s2) -> s + s2).orElseThrow()));
        } else {
            return stringStream
                    .map(Long::parseLong)
                    .toList();
        }
    }
}

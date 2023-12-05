package com.dixit.soham.aoc.day5.seeds;

import com.dixit.soham.aoc.common.Solver;
import org.apache.commons.lang3.Range;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SeedsSolver extends Solver {

    private final Pattern SEEDS_REGEX = Pattern.compile("seeds:(( \\d+)+)");
    private final Pattern MAPS_REGEX = Pattern.compile("(\\w+)-to-(\\w+) map:");
    private final Pattern RANGE_REGEX = Pattern.compile("(\\d+) (\\d+) (\\d+)");

    private List<Range<Long>> allSeedRanges;
    private final boolean seedRanges;
    private List<Map<Range<Long>, Range<Long>>> allMaps;

    public SeedsSolver(InputStream inputStream) {
        super(inputStream);
        this.allMaps = new LinkedList<>();
        this.seedRanges = false;
    }

    public SeedsSolver(InputStream inputStream, boolean seedRanges) {
        super(inputStream);
        this.allMaps = new LinkedList<>();
        // Flag which indicates whether to read seed input as ranges (for part 2)
        this.seedRanges = seedRanges;
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        Matcher seedsMatcher = SEEDS_REGEX.matcher(line);
        if (seedsMatcher.matches()) {
            List<Long> seedRanges1 = Arrays.stream(seedsMatcher.group(1).split(" "))
                    .filter(s -> !s.isBlank())
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            this.allSeedRanges = parse(seedRanges1, seedRanges);
        }

        line = reader.readLine();

        Map<Range<Long>, Range<Long>> currentMap = null;
        while (line != null) {

            if (MAPS_REGEX.matcher(line).matches()) {
                currentMap = new HashMap<>();
                allMaps.add(currentMap);
            }

            Matcher rangeMatcher = RANGE_REGEX.matcher(line);
            if (rangeMatcher.matches()) {
                Long destinationRangeStart = Long.parseLong(rangeMatcher.group(1));
                Long sourceRangeStart = Long.parseLong(rangeMatcher.group(2));
                Long rangeLength = Long.parseLong(rangeMatcher.group(3));

                assert currentMap != null;
                currentMap.put(
                        Range.of(sourceRangeStart, sourceRangeStart + rangeLength - 1),
                        Range.of(destinationRangeStart, destinationRangeStart + rangeLength - 1)
                );
            }
            // read next line
            line = reader.readLine();
        }

        System.out.println("All Maps are - ");
        allMaps.forEach(System.out::println);
        System.out.println();

        reader.close();
    }

    private List<Range<Long>> parse(List<Long> seedRanges1, boolean seedRanges) {
        if (!seedRanges) {
            return seedRanges1.stream()
                    .map(aLong -> Range.of(aLong, aLong))
                    .collect(Collectors.toList());
        } else {
            List<Range<Long>> allSeedRanges = new ArrayList<>();
            for (int i = 0; i < seedRanges1.size(); i += 2) {
                Long seedRangeStart = seedRanges1.get(i);
                long seedRangeEnd = seedRangeStart + seedRanges1.get(i + 1) - 1;
                allSeedRanges.add(Range.of(seedRangeStart, seedRangeEnd));
            }
            return allSeedRanges;
        }
    }

    public long getLowestLocationNumber() {
        List<Range<Long>> processed = new ArrayList<>(allSeedRanges);
        int i = 1;
        for (Map<Range<Long>, Range<Long>> m : allMaps) {
            System.out.printf("--------------------- %s ---------------------%n", i);
            processed = preprocess(processed, m).stream().toList();
            processed = findOverlapsAndMapToDestination(processed, m);
            processed.sort(Comparator.comparing(Range::getMinimum));
            System.out.printf("Processed is %s%n", processed);
            i++;
        }

        processed.sort(Comparator.comparing(Range::getMinimum));

        return processed.get(0).getMinimum();
    }

    private Set<Range<Long>> preprocess(List<Range<Long>> processed, Map<Range<Long>, Range<Long>> m) {
        Set<Range<Long>> l2 = new HashSet<>();
        for (Range<Long> r1 : processed) {
            boolean found = false;
            for (Range<Long> r2 : m.keySet()) {
                if (r1.isOverlappedBy(r2)) {
                    Range<Long> intersect = r1.intersectionWith(r2);
                    l2.add(intersect);
                    l2.addAll(findUncovered(r1, intersect));
                    found = true;
                }
            }
            if (!found) {
                l2.add(r1);
            }
        }
        return l2;
    }

    private Collection<? extends Range<Long>> findUncovered(Range<Long> r1, Range<Long> intersect) {
        List<Range<Long>> uncovered = new ArrayList<>();

        if (!Objects.equals(intersect.getMinimum(), r1.getMinimum())) {
            uncovered.add(Range.of(r1.getMinimum(), intersect.getMinimum() - 1));
        }

        if (!Objects.equals(intersect.getMaximum(), r1.getMaximum())) {
            uncovered.add(Range.of(intersect.getMaximum() + 1, r1.getMaximum()));
        }

        return uncovered;
    }

    private List<Range<Long>> findOverlapsAndMapToDestination(List<Range<Long>> list1, Map<Range<Long>, Range<Long>> list2) {
        List<Range<Long>> destinations = new ArrayList<>();
        for (Range<Long> r1 : list1) {
            boolean found = false;
            List<Range<Long>> mapped = new ArrayList<>();
            for (Range<Long> r2 : list2.keySet()) {
                if (r1.isOverlappedBy(r2)) {
                    Range<Long> intersect = r1.intersectionWith(r2);
                    long diff = intersect.getMinimum() - r2.getMinimum();
                    long intersectSize = intersect.getMaximum() - intersect.getMinimum();
                    Range<Long> destRange = list2.get(r2);
                    Range<Long> destination = Range.of(destRange.getMinimum() + diff, destRange.getMinimum() + diff + intersectSize);
                    mapped.add(destination);
                    System.out.printf("%s mapped to %s%n", r1, mapped);
                    found = true;
                    break;
                }
            }
            if (!found) {
                mapped.add(r1);
            }
            destinations.addAll(mapped);
        }
        return destinations;
    }

}

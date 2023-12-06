package com.dixit.soham.aoc.day6.boats;

public class Race {

    long time;
    long recordDistance;

    public Race(long time, long recordDistance) {
        this.time = time;
        this.recordDistance = recordDistance;
    }

    @Override
    public String toString() {
        return "Race{" +
                "time=" + time +
                ", recordDistance=" + recordDistance +
                '}';
    }
}

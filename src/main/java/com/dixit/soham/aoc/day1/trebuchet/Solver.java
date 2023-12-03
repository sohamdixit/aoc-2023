package com.dixit.soham.aoc.day1.trebuchet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.lang.Character.isDigit;

public class Solver {

    private final int sumOfCalibrationVals;
    private final List<String> numsSpelledOut =
            List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private final List<String> numsSpelledOutMod =
            List.of("zeroo", "onee", "twoo", "threee", "four", "fivee", "six", "seven", "eightt", "ninee");

    public Solver(InputStream inputStream, boolean preprocess) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = reader.readLine();
        int sumOfCalibrationVals = 0;

        while (line != null) {
            int calibrationVal = findCalibrationVal(preprocess ? preprocess(line) : line);
            sumOfCalibrationVals += calibrationVal;
            // read next line
            line = reader.readLine();
        }

        this.sumOfCalibrationVals = sumOfCalibrationVals;

        reader.close();
    }

    private String preprocess(String line) {

        StringBuilder b = new StringBuilder(line);
        for(int i=0 ; i<numsSpelledOut.size() ; i++) {
            String replaced = b.toString().replaceAll(numsSpelledOut.get(i), numsSpelledOutMod.get(i));
            b.delete(0, b.length());
            b.append(replaced);
        }

        for(int i=0 ; i<numsSpelledOut.size() ; i++) {
            String replaced = b.toString().replaceAll(numsSpelledOut.get(i), "" + i);
            b.delete(0, b.length());
            b.append(replaced);
        }
        return  b.toString();

    }

    public int getSumOfCalibrationVals() {
        return sumOfCalibrationVals;
    }

    private int findCalibrationVal(String line) {

        int firstDigit = -1;
        for(int i=0 ; i<line.length() ; i++) {
            if(isDigit(line.charAt(i))) {
                firstDigit = Integer.parseInt(""+line.charAt(i));
                break;
            }
        }

        int lastDigit = -1;
        for(int i=line.length()-1 ; i>=0 ; i--) {
            if(isDigit(line.charAt(i))) {
                lastDigit = Integer.parseInt(""+line.charAt(i));
                break;
            }
        }

        return (firstDigit * 10) + lastDigit;
    }

}

package org.example.numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        ArrayList<Integer> values = new ArrayList<>();

        String[] separatedValues = input.split(",");
        for (String separatedValue : separatedValues) {
            try {
                int value = Integer.parseInt(separatedValue);
                values.add(value);
            } catch (NumberFormatException e) {
                System.out.println(separatedValue + " cannot be converted into integer.");
            }
        }

        // Sort values in ascending order
        Comparator<Integer> ascendingOrder = (v1, v2) -> v1 - v2;
        values.sort(ascendingOrder);
        return values;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        return "";
    }
}

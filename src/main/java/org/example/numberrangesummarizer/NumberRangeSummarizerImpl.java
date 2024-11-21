package org.example.numberrangesummarizer;

import java.util.*;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        Set<Integer> valuesSet = new HashSet<>();

        String[] separatedValues = input.split(",");
        for (String separatedValue : separatedValues) {
            try {
                separatedValue = separatedValue.strip();
                int value = Integer.parseInt(separatedValue);
                valuesSet.add(value);
            } catch (NumberFormatException e) {
                System.out.println("'" + separatedValue + "' cannot be converted into integer.");
            }
        }

        ArrayList<Integer> values = new ArrayList<>(valuesSet);
        // Sort values in ascending order
        Comparator<Integer> ascendingOrder = (v1, v2) -> v1 - v2;
        values.sort(ascendingOrder);

        return values;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        // Ensure that there are values in the input collection
        if (input.isEmpty()) {
            return "";
        }
        ArrayList<String> output = new ArrayList<>();

        ArrayList<Integer> inputList = new ArrayList<>(input);
        int start = 0;
        for (int i = 1; i <= inputList.size(); i++) {
            if (i == inputList.size() || inputList.get(i) != (inputList.get(i - 1) + 1)) {
                if (start == i - 1) {
                    output.add(String.valueOf(inputList.get(start)));
                } else {
                    output.add(inputList.get(start) + "-" + inputList.get(i - 1));
                }
                start = i;
            }
        }

        return String.join(", ", output);
    }
}

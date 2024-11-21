package org.example.numberrangesummarizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerImplTest {

    @Test
    void collect() {
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));
        ArrayList<Integer> duplicateExpectedList = new ArrayList<>(Arrays.asList(1, 3, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));

        NumberRangeSummarizerImpl rs = new NumberRangeSummarizerImpl();

        assertAll(
                // Test empty input: should return an empty list
                () -> assertEquals(new ArrayList<Integer>(), rs.collect("")),

                // Test a valid comma-separated sequence of integers
                () -> assertEquals(expectedList, rs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test an unordered input: should return the same list in sorted order
                () -> assertEquals(expectedList, rs.collect("1,6,7,8,3,12,13,14,21,15,23,22,24,31")),

                // Test an input with duplicate values: should return the same list in sorted order
                () -> assertEquals(duplicateExpectedList, rs.collect("1,3,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test input containing invalid characters: should ignore non-integer values
                () -> assertEquals(expectedList, rs.collect("1,a, ,!,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test input with leading and trailing spaces: should remove whitespaces and process correctly
                () -> assertEquals(expectedList, rs.collect(" 1,6 , 7 ,8,3,12,13,14,21,15,23,22,24,31")));
    }

    @Test
    void summarizeCollection() {
        NumberRangeSummarizerImpl rs = new NumberRangeSummarizerImpl();

        Collection<Integer> emptyInput = rs.collect("");
        String expectedEmptyOutput = "";

        Collection<Integer> defaultInput = rs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String expectedDefaultOutput = "1, 3, 6-8, 12-15, 21-24, 31";

        Collection<Integer> testInput = rs.collect("0,1,2,3,5,7,8,9,11,12,13,15,21,27,28,29,30,31");
        String expectTestOutput = "0-3, 5, 7-9, 11-13, 15, 21, 27-31";

        assertAll(
                // Test empty input: should return empty string
                () -> assertEquals(expectedEmptyOutput, rs.summarizeCollection(emptyInput)),

                // Test a valid comma-separated sequence of integers
                () -> assertEquals(expectedDefaultOutput, rs.summarizeCollection(defaultInput)),

                // Test an unordered input: should return same list as the default list
                () -> {
                    Collection<Integer> unorderedInput = rs.collect("1,6,7,8,3,12,13,14,21,15,23,22,24,31");
                    assertEquals(expectedDefaultOutput, rs.summarizeCollection(unorderedInput));
                    },

                // Test an input with duplicate values: should return the correct ranges without any overlapped or repeated ranges
                () -> {
                    Collection<Integer> duplicateInput = rs.collect("1,3,3,6,6,7,8,12,13,14,15,21,22,23,24,31");
                    assertEquals(expectedDefaultOutput, rs.summarizeCollection(duplicateInput));
                    },

                // Test input containing invalid characters: should ignore non-integer values
                () -> {
                    Collection<Integer> invalidCharactersInput = rs.collect("1,a, ,!,3,6,7,8,12,13,14,15,21,22,23,24,31");
                    assertEquals(expectedDefaultOutput, rs.summarizeCollection(invalidCharactersInput));
                },

                // Test input with leading and trailing spaces: should process correctly
                () -> {
                    Collection<Integer> whitespacesInput = rs.collect(" 1,3,6 , 7 ,8,12,13,14,21,15,23,22,24,31");
                    assertEquals(expectedDefaultOutput, rs.summarizeCollection(whitespacesInput));
                },

                // Test with different test input to verify it works with input that is different to the sample input
                () -> {
                    assertEquals(expectTestOutput, rs.summarizeCollection(testInput));
                }
        );
    }
}
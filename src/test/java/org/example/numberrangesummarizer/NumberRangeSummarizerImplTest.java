package org.example.numberrangesummarizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerImplTest {

    @Test
    void collect() {
        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));
        ArrayList<Integer> duplicateExpectedList = new ArrayList<>(Arrays.asList(1, 3, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31));

        NumberRangeSummarizerImpl rs = new NumberRangeSummarizerImpl();

        assertAll(
                // Test empty input: should return an empty list
                ()-> assertEquals(new ArrayList<Integer>(), rs.collect("")),

                // Test a valid comma-separated sequence of integers
                ()-> assertEquals(expectedList, rs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test an unordered input: should return the same list in sorted order
                ()-> assertEquals(expectedList, rs.collect("1,6,7,8,3,12,13,14,21,15,23,22,24,31")),

                // Test an input with duplicate values: should return the same list in sorted order
                ()-> assertEquals(duplicateExpectedList, rs.collect("1,3,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test input containing invalid characters: should ignore non-integer values
                ()-> assertEquals(expectedList, rs.collect("1,a, ,!,3,6,7,8,12,13,14,15,21,22,23,24,31")),

                // Test input with leading and trailing spaces: should remove whitespaces and process correctly
                ()-> assertEquals(expectedList, rs.collect(" 1,6 , 7 ,8,3,12,13,14,21,15,23,22,24,31")));
    }

    @Test
    void summarizeCollection() {

    }
}
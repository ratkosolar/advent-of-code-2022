package com.ratkosolar.adventofcode2022.day04;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.stream;

@Component
public class CampCleanup {

    public Integer calculateNumberOfFullyOverlappingAssignmentPairs(String input) {
        var assignmentPairs = mapStringToAssignmentPairs(input);
        return assignmentPairs.stream()
            .filter(a ->
                (a.start1 <= a.start2 && a.end1 >= a.end2) ||
                (a.start1 >= a.start2 && a.end1 <= a.end2)
            )
            .toList()
            .size();
    }

    public Integer calculateNumberOfPartiallyOverlappingAssignmentPairs(String input) {
        var assignmentPairs = mapStringToAssignmentPairs(input);
        return assignmentPairs.stream()
            .filter(a -> a.start1 <= a.end2 && a.end1 >= a.start2)
            .toList()
            .size();
    }

    private List<AssignmentPair> mapStringToAssignmentPairs(String input) {
        return stream(input.split("\\r\\n"))
            .map(line -> {
                var ranges = stream(line.split(","))
                    .flatMap(x -> stream(x.split("-")))
                    .mapToInt(Integer::valueOf)
                    .toArray();
                return new AssignmentPair(ranges[0], ranges[1], ranges[2], ranges[3]);
            })
            .toList();
    }

    private record AssignmentPair(Integer start1, Integer end1, Integer start2, Integer end2) {
    }
}

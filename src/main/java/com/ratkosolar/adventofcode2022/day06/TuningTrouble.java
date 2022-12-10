package com.ratkosolar.adventofcode2022.day06;

import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class TuningTrouble {

    public Integer calculateNumberOfCharactersBeforeFirstMarkerOccurrence(String input) {
        return calculateFirstOccurrenceOfUniqueCharacters(input, 4);
    }

    public Integer calculateNumberOfCharactersBeforeFirstMessageOccurrence(String input) {
        return calculateFirstOccurrenceOfUniqueCharacters(input, 14);
    }

    private Integer calculateFirstOccurrenceOfUniqueCharacters(String input, Integer numberOfUniqueCharacters) {
        var firstOccurrenceIndex = IntStream.range(0, input.length() - numberOfUniqueCharacters)
            .filter(i -> input.substring(i, i + numberOfUniqueCharacters).chars().distinct().count() == numberOfUniqueCharacters)
            .findFirst()
            .getAsInt();
        return firstOccurrenceIndex + numberOfUniqueCharacters;
    }
}

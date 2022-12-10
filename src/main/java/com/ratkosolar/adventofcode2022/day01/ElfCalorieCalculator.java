package com.ratkosolar.adventofcode2022.day01;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class ElfCalorieCalculator {
    public Long calculateTheMostCaloriesCarriedByOneElf(String input) {
        return getElfs(input).stream()
            .mapToLong(Elf::totalCalories)
            .max().orElseThrow(NoSuchElementException::new);
    }

    public Long calculateTheMostCaloriesCarriedByThreeElfs(String input) {
        var caloriesOrdered = getElfs(input).stream()
            .mapToLong(Elf::totalCalories)
            .sorted()
            .toArray();
        return caloriesOrdered[caloriesOrdered.length - 1] +
            caloriesOrdered[caloriesOrdered.length - 2] +
            caloriesOrdered[caloriesOrdered.length - 3];
    }

    private static List<Elf> getElfs(String input) {
        return stream(input.split("\\r\\n\\r\\n"))
            .map(caloriesList ->
                new Elf(stream(caloriesList.split("\\r\\n"))
                    .mapToLong(Long::valueOf)
                    .sum()
                )
            )
            .toList();
    }

    private record Elf(Long totalCalories) {
    }
}

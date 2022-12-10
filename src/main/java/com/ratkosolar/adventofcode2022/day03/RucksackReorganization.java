package com.ratkosolar.adventofcode2022.day03;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

@Component
public class RucksackReorganization {

    public Integer calculateTotalSumOfUnorganizedItemPriorities(String input) {
        return stream(input.split("\\r\\n"))
            .mapToInt(rucksac ->
                getDuplicateItemTypesBetweenCompartments(rucksac).stream()
                    .mapToInt(this::getItemPriority)
                    .sum()
            )
            .sum();
    }

    public Integer calculateTotalSumOfGroupBadgeItemPriorities(String input) {
        var rucksacks = stream(input.split("\\r\\n"))
            .map(this::mapStringToHashSet)
            .toList();
        var groups = splitIntoGroupsOfThree(rucksacks);
        return groups.stream()
            .mapToInt(group -> {
                var groupBadgeItem = getGroupBadgeItem(group);
                return getItemPriority(groupBadgeItem);
            })
            .sum();
    }

    private List<Character> getDuplicateItemTypesBetweenCompartments(String rucksack) {
        var compartment1 = mapStringToHashSet(rucksack.substring(0, rucksack.length() / 2));
        var compartment2 = mapStringToHashSet(rucksack.substring(rucksack.length() / 2));
        return compartment1.stream().filter(compartment2::contains).toList();
    }

    private List<List<HashSet<Character>>> splitIntoGroupsOfThree(List<HashSet<Character>> rucksacks) {
        return IntStream.range(0, rucksacks.size())
            .mapToObj(index -> new Tuple(index, rucksacks.get(index)))
            .collect(Collectors.groupingBy((tuple) -> Math.floor(tuple.index / 3)))
            .values().stream()
            .map(tuples ->
                tuples.stream()
                    .map(tuple -> tuple.items)
                    .toList()
            )
            .toList();
    }

    private Character getGroupBadgeItem(List<HashSet<Character>> groupRucksacks) {
        return groupRucksacks.get(0).stream()
            .filter(item ->
                groupRucksacks.stream()
                    .allMatch(rucksack -> rucksack.contains(item))
            ).findFirst().orElseThrow();
    }

    private Integer getItemPriority(Character item) {
        return (int) item - (Character.isUpperCase(item) ? 38 : 96);
    }

    private HashSet<Character> mapStringToHashSet(String s) {
        return s.chars().mapToObj(x -> (char) x).collect(Collectors.toCollection(HashSet::new));
    }

    private record Tuple(Integer index, HashSet<Character> items) {
    }
}

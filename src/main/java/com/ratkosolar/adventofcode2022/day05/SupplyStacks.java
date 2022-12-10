package com.ratkosolar.adventofcode2022.day05;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static java.util.Collections.reverse;

@Component
public class SupplyStacks {
    public String calculateTopCratesAfterRearrangement(String input) {
        var inputParts = input.split("\\r\\n\\r\\n");
        var stacks = getStacks(inputParts[0].split("\\r\\n"));
        var commands = getRearrangeCommands(inputParts[1].split("\\r\\n"));
        commands.stream().forEach(command -> {
            IntStream.range(0, command.numberOfCrates).forEach((i) -> {
                var crate = stacks.get(command.from()).removeLast();
                stacks.get(command.to()).addLast(crate);
            });
        });
        return getTopContainers(stacks);
    }

    public String calculateTopCratesAfterRearrangementV9001(String input) {
        var inputParts = input.split("\\r\\n\\r\\n");
        var stacks = getStacks(inputParts[0].split("\\r\\n"));
        var commands = getRearrangeCommands(inputParts[1].split("\\r\\n"));
        commands.stream().forEach(command -> {
            var crates = IntStream.range(0, command.numberOfCrates)
                .mapToObj((i) -> stacks.get(command.from()).removeLast())
                .collect(Collectors.toCollection(ArrayDeque::new));
            IntStream.range(0, crates.size())
                .forEach(i -> stacks.get(command.to()).addLast(crates.removeLast()));
        });
        return getTopContainers(stacks);
    }

    private Map<String, ArrayDeque<String>> getStacks(String[] cratesAndStacks) {
        reverse(asList(cratesAndStacks));
        var stacksAndCratesMatrix = stream(cratesAndStacks)
            .map(line -> stream(
                line.split("(?<=\\G.{3})\\s?"))
                .map(s -> s.trim().replaceAll("(\\[|\\])", ""))
                .toList()
            )
            .toList();
        return IntStream.range(0, stacksAndCratesMatrix.get(0).size())
            .mapToObj(x -> new SupplyStack(
                stacksAndCratesMatrix.get(0).get(x),
                IntStream.range(1, stacksAndCratesMatrix.size())
                    .mapToObj(y -> stacksAndCratesMatrix.get(y).get(x))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toCollection(ArrayDeque::new))
            ))
            .collect(Collectors.toMap(supplyStack -> supplyStack.name(), supplyStack -> supplyStack.crates()));
    }

    private List<RearrangeCommand> getRearrangeCommands(String[] commands) {
        return stream(commands).map(line -> {
            var parts = line.split(" ");
            return new RearrangeCommand(
                parts[3],
                parts[5],
                Integer.valueOf(parts[1])
            );
        }).toList();
    }

    private String getTopContainers(Map<String, ArrayDeque<String>> stacks) {
        return stacks.values().stream()
            .map(ArrayDeque::getLast)
            .collect(Collectors.joining());
    }

    public record SupplyStack(String name, ArrayDeque<String> crates) {
    }

    public record RearrangeCommand(String from, String to, Integer numberOfCrates) {
    }
}

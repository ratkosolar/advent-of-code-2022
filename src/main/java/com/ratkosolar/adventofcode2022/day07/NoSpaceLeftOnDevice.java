package com.ratkosolar.adventofcode2022.day07;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class NoSpaceLeftOnDevice {

    public Long calculateTotalSizeOfDirectoriesBelow100000(String input) {
        return getDirectories(input).values()
            .stream()
            .filter(size -> size <= 100_000)
            .mapToLong(x -> x)
            .sum();
    }

    public Long findTheSizeOfDirectoryToBeDeleted(String input) {
        var totalDiskSpace = 70_000_000;
        var targetFreeSpace = 30_000_000;
        var directories = getDirectories(input);
        var currentFreeSpace = totalDiskSpace - directories.get("/");
        var targetFreeSpaceDelta = targetFreeSpace - currentFreeSpace;
        return directories.values()
            .stream()
            .filter(size -> size >= targetFreeSpaceDelta)
            .sorted()
            .findFirst()
            .orElseThrow();
    }

    private Map<String, Long> getDirectories(String input) {
        var fileSystem = new FileSystem();
        Arrays.stream(input.split("\\r\\n")).forEach(line -> {
            var chunks = line.split(" ");
            if (line.startsWith("$ cd")) {
                fileSystem.changeDirectories(chunks[2]);
            } else if (Character.isDigit(line.charAt(0))) {
                fileSystem.addFileSize(Long.valueOf(chunks[0]));
            }
        });
        return fileSystem.directories;
    }

    private class FileSystem {
        String currentPath = "";
        Map<String, Long> directories = new HashMap<>();

        FileSystem() {
        }

        public void addFileSize(Long size) {
            directories.keySet().stream()
                .filter(path -> currentPath.startsWith(path))
                .forEach(path -> {
                    directories.compute(path, (key, totalSize) -> totalSize + size);
                });
        }

        public void changeDirectories(String newPath) {
            if ("..".equals(newPath)) {
                currentPath = currentPath.substring(0, Math.max(currentPath.lastIndexOf('/'), 1));
            } else {
                currentPath += (currentPath.length() <= 1 ? "" : "/") + newPath;
            }

            if (directories.get(currentPath) == null) {
                directories.put(currentPath, 0L);
            }
        }
    }
}

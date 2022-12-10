package com.ratkosolar.adventofcode2022.day04;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CampCleanupTest {

    @Autowired
    CampCleanup campCleanup;

    @Value("classpath:day04-input")
    private Resource input;

    @Test
    void calculateNumberOfFullyOverlappingAssignmentPairs() {
        var result = campCleanup.calculateNumberOfFullyOverlappingAssignmentPairs(ResourceReader.asString(input));
        assertThat(result).isEqualTo(518);
    }

    @Test
    void calculateNumberOfPartiallyOverlappingAssignmentPairs() {
        var result = campCleanup.calculateNumberOfPartiallyOverlappingAssignmentPairs(ResourceReader.asString(input));
        assertThat(result).isEqualTo(909);
    }

}
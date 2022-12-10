package com.ratkosolar.adventofcode2022.day01;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ElfCalorieCalculatorTest {

    @Autowired
    private ElfCalorieCalculator elfCalorieCalculator;

    @Value("classpath:day01-input")
    private Resource input;

    @Test
    void calculateTheMostCaloriesCarriedByOneElf() {
        var result = elfCalorieCalculator.calculateTheMostCaloriesCarriedByOneElf(ResourceReader.asString(input));
        assertThat(result).isEqualTo(70369);
    }

    @Test
    void calculateTheMostCaloriesCarriedByThreeElfs() {
        var result = elfCalorieCalculator.calculateTheMostCaloriesCarriedByThreeElfs(ResourceReader.asString(input));
        assertThat(result).isEqualTo(203002);
    }

}
package com.ratkosolar.adventofcode2022.day03;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RucksackReorganizationTest {

    @Autowired
    RucksackReorganization rucksackReorganization;

    @Value("classpath:day03-input")
    private Resource input;

    @Test
    void calculateTotalSumOfPriorities() {
        var result = rucksackReorganization.calculateTotalSumOfUnorganizedItemPriorities(ResourceReader.asString(input));
        assertThat(result).isEqualTo(8109);
    }

    @Test
    void calculateTotalSumOfGroupBadgeItemPriorities() {
        var result = rucksackReorganization.calculateTotalSumOfGroupBadgeItemPriorities(ResourceReader.asString(input));
        assertThat(result).isEqualTo(2738);
    }

}
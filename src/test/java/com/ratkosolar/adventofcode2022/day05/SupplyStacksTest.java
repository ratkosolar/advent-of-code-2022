package com.ratkosolar.adventofcode2022.day05;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SupplyStacksTest {

    @Autowired
    SupplyStacks supplyStacks;

    @Value("classpath:day05-input")
    private Resource input;

    @Test
    void calculateTopCratesAfterRearrangement() {
        var result = supplyStacks.calculateTopCratesAfterRearrangement(ResourceReader.asString(input));
        assertThat(result).isEqualTo("HNSNMTLHQ");
    }

    @Test
    void calculateTopCratesAfterRearrangementV9001() {
        var result = supplyStacks.calculateTopCratesAfterRearrangementV9001(ResourceReader.asString(input));
        assertThat(result).isEqualTo("RNLFDJMCT");
    }

}
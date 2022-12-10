package com.ratkosolar.adventofcode2022.day06;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TuningTroubleTest {

    @Autowired
    TuningTrouble tuningTrouble;

    @Value("classpath:day06-input")
    private Resource input;

    @Test
    void calculateNumberOfCharactersBeforeFirstMarkerOccurrence() {
        var result = tuningTrouble.calculateNumberOfCharactersBeforeFirstMarkerOccurrence(ResourceReader.asString(input));
        assertThat(result).isEqualTo(1042);
    }

    @Test
    void calculateNumberOfCharactersBeforeFirstMessageOccurrence() {
        var result = tuningTrouble.calculateNumberOfCharactersBeforeFirstMessageOccurrence(ResourceReader.asString(input));
        assertThat(result).isEqualTo(2980);
    }

}
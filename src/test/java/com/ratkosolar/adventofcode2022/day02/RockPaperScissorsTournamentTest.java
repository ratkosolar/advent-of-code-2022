package com.ratkosolar.adventofcode2022.day02;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RockPaperScissorsTournamentTest {

    @Autowired
    RockPaperScissorsTournament rockPaperScissorsTournament;

    @Value("classpath:day02-input")
    private Resource input;

    @Test
    void calculateTotalScoreV1() {
        var result = rockPaperScissorsTournament.calculateTotalScoreV1(ResourceReader.asString(input));
        assertThat(result).isEqualTo(12679);
    }

    @Test
    void calculateTotalScoreV2() {
        var result = rockPaperScissorsTournament.calculateTotalScoreV2(ResourceReader.asString(input));
        assertThat(result).isEqualTo(14470);
    }

}
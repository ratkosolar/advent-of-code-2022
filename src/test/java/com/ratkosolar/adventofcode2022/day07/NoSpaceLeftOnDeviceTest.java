package com.ratkosolar.adventofcode2022.day07;

import com.ratkosolar.adventofcode2022.utils.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoSpaceLeftOnDeviceTest {

    @Autowired
    NoSpaceLeftOnDevice noSpaceLeftOnDevice;

    @Value("classpath:day07-input")
    private Resource input;

    @Test
    void calculateTotalSizeOfDirectoriesBelow100000() {
        var result = noSpaceLeftOnDevice.calculateTotalSizeOfDirectoriesBelow100000(ResourceReader.asString(input));
        assertThat(result).isEqualTo(1297159);
    }

    @Test
    void findTheSizeOfDirectoryToBeDeleted() {
        var result = noSpaceLeftOnDevice.findTheSizeOfDirectoryToBeDeleted(ResourceReader.asString(input));
        assertThat(result).isEqualTo(3866390);
    }

}
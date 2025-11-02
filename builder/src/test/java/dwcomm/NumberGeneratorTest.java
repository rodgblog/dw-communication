package dwcomm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dwcomm.generator.NumberGenerator;

import java.util.List;

class NumberGeneratorTest {

    private final NumberGenerator numberGenerator = new NumberGenerator();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generate() {
        Assertions.assertNotNull(numberGenerator.generate());
        Assertions.assertNotNull(numberGenerator.generate());
        Assertions.assertNotNull(numberGenerator.generate());
    }

    @Test
    void testGenerate() {
        List<Long> generate = numberGenerator.generate(500);
        Assertions.assertNotNull(generate);
    }
}
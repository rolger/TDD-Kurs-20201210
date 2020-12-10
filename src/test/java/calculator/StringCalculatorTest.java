package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    void shouldRunAndFail() {
        Assertions.assertThat("Hello").isEqualTo("");
    }
}

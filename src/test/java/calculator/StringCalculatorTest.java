package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    private static Stream<Arguments> provideArgumentsForAdd() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 1", valueOf(2)),
                Arguments.of("1, 2, 3, 4", valueOf(10))
        );
    }

    private static Stream<Arguments> provideArgumentsForSubtract() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 1", valueOf(0)),
                Arguments.of("11,2,17", valueOf(-8))
        );
    }

    private static Stream<Arguments> provideArgumentsForSubtractR() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 0", valueOf(-1)),
                Arguments.of("1, 2, 4", valueOf(1))
        );
    }

    private static Stream<Arguments> provideArgumentsForMultiply() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 0", valueOf(0)),
                Arguments.of("1, 2, 4, 3", valueOf(24))
        );
    }

    private static Stream<Arguments> provideArgumentsForDivide() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 0", valueOf(1)),
                Arguments.of("1, 2, 4", valueOf(0.125))
        );
    }

    private static Stream<Arguments> provideArgumentsForDivideR() {
        return Stream.of(
                Arguments.of("1", valueOf(1)),
                Arguments.of("1, 0", valueOf(0)),
                Arguments.of("4, 2, 4", valueOf(0.5))
        );
    }

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForAdd")
    void add_shouldReturnSumOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.add(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1, c"})
    void add_whenInputIsInvalid_throwsException(String invalidInput) {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.add(invalidInput));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSubtract")
    void substract_shouldReturnDifferenceOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.subtract(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSubtractR")
    void substract_shouldReturnReverseDifferenceOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.subtractR(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1, c"})
    void substract_whenInputIsInvalid_throwsException(String invalidInput) {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.subtract(invalidInput));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForMultiply")
    void multiply_shouldReturnProductOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.multiply(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForDivide")
    void divide_shouldReturnSumOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.divide(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1, c"})
    void divide_whenInputIsInvalid_throwsException(String invalidInput) {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.divide(invalidInput));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForDivideR")
    void divideR_shouldReturnSumOfNumbers(String input, BigDecimal expectedResult) {
        assertThat(stringCalculator.divideR(input)).isEqualTo(expectedResult);
    }

    @Test
    void getNumbers_shouldSplitBySpaceAndTrimWhitespaces() {
        assertThat(stringCalculator.getNumbers("1 2, 3da2   ")).contains("1", "2,", "3da2");
    }
}

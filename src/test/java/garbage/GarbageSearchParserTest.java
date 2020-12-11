package garbage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GarbageSearchParserTest {

    private GarbageSearchParser sut;

    @BeforeEach
    void setUp() {
         sut = new GarbageSearchParser();
    }

    @Test
    void singleWordShouldReturnUppercaseKeyword() {
        assertThat(sut.parse("ball")).hasSize(1).contains("BALL");
        assertThat(sut.parse("gras")).hasSize(1).contains("GRAS");
    }

    @Test
    void invalidInputShouldReturnEmptyList() {
        assertThat(sut.parse(null)).isEmpty();
        assertThat(sut.parse("")).isEmpty();
        assertThat(sut.parse("1")).isEmpty();
        assertThat(sut.parse("1456")).isEmpty();
    }

    @Test
    void multipleWordsShouldReturnUppercaseKeywords() {
        assertThat(sut.parse("ball, gras"))
                .hasSize(2)
                .contains("BALL", "GRAS");
    }

    @Test
    void shouldIgnoreAdditionalEmptyInputs() {
        assertThat(sut.parse("SCHROTT, , , , "))
                .hasSize(1)
                .contains("SCHROTT");
    }

    @Test
    void shouldFilterDuplicates() {
        assertThat(sut.parse("gras, GRAS"))
                .hasSize(1)
                .contains("GRAS");
    }

    @Test
    void shouldParseLocation() {
        assertThat(sut.parse("1 a"))
                .hasSize(1)
                .contains("1-A");

        assertThat(sut.parse("1 B"))
                .hasSize(1)
                .contains("1-B");

        assertThat(sut.parse("1 B, 1 c"))
                .hasSize(2)
                .contains("1-B", "1-C");
    }

}

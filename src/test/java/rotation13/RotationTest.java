package rotation13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RotationTest {

    private Rotation sut;

    /*
     * - zahlen werden auch rotiert (optional)
     */

    @BeforeEach
    void setUp() {
        sut = new Rotation();
    }

    @Test
    public void shouldEncryptIncludingNumbers() {
        Rotation rotation = new Rotation(1, Rotation::encryptWithNumbers);
        assertThat(rotation.encrypt("0a")).isEqualTo("1B");
    }

    @Test
    public void shouldThrowInvalidParameterException() {
        assertThatThrownBy(() -> new Rotation(0)).isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> new Rotation(26)).isInstanceOf(InvalidParameterException.class);
    }
    
    @Test
    public void shouldEncryptByDefinedShiftFactor() {
        Rotation rotation1 = new Rotation(1);
        assertThat(rotation1.encrypt("a")).isEqualTo("B");
    }

    @Test
    public void shouldEncryptUppercase() {
        assertThat(sut.encrypt("E")).isEqualTo("R");
        assertThat(sut.encrypt("F")).isEqualTo("S");
    }

    @Test
    public void shouldEncryptLowercase() {
        assertThat(sut.encrypt("e")).isEqualTo("R");
    }

    @Test
    public void shouldEncryptWord() {
        assertThat(sut.encrypt("ell")).isEqualTo("RYY");
    }

    @Test
    public void shouldEncryptWhenOverflow() {
        assertThat(sut.encrypt("n")).isEqualTo("A");
    }

    @Test
    public void shouldNotEncryptNonLetters() {
        assertThat(sut.encrypt("1 ,")).isEqualTo("1 ,");
    }

    @Test
    public void shouldEncryptUmlaute() {
        assertThat(sut.encrypt("ÖÄÜß")).isEqualTo("BRNRHRFF");
    }

    @Test
    public void shouldNotEncryptNull() {
        assertThat(sut.encrypt(null)).isEqualTo(null);
    }
}

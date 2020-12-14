package rotation13;

import java.security.InvalidParameterException;
import java.util.Locale;
import java.util.function.Function;

public class Rotation {

    public static final int ALPHABET_COUNT = 26;
    public static final int DEFAULT_ENCRYPT_SHIFT = 13;
    public static final char Z = 'Z';

    private final int rotation;
    private final IEncryptor encryptor;

    @FunctionalInterface
    interface IEncryptor {
        Character encrypt(int rotation, Character input);
    }

    public Rotation() {
        this(DEFAULT_ENCRYPT_SHIFT, Rotation::encryptChar);
    }

    public Rotation(int rotation) {
        this(rotation, Rotation::encryptChar);
    }

    public Rotation(int rotation, IEncryptor encryptor) {
        if (rotation < 1 || rotation > 25) {
            throw new InvalidParameterException();
        }
        this.rotation = rotation;
        this.encryptor = encryptor;
    }

    public String encrypt(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for(char aChar : sanitizeInput(input).toCharArray()) {
            result.append(encryptor.encrypt(rotation, aChar));
        }
        return result.toString();
    }

    private String sanitizeInput(String input) {
        return input.toUpperCase(Locale.ROOT)
                .replaceAll("Ä", "AE")
                .replaceAll("Ö", "OE")
                .replaceAll("Ü", "UE")
                .replaceAll("ß", "SS");
    }

    public static char encryptChar(int rotation, char c) {
        if (!Character.isLetter(c)) {
            return c;
        }
        char encryptResult = (char) (c + rotation);
        if (encryptResult > Z) {
            encryptResult -= ALPHABET_COUNT;
        }
        return encryptResult;
    }

    public static Character encryptWithNumbers(int rotation, Character character) {
        return 0;
    }

}

package garbage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class GarbageSearchParser {

    public static final Pattern LOCATION_PATTERN = Pattern.compile("(\\d)\\s(\\w)");

    public List<String> parse(String input) {
        if (isInvalidInput(input)) {
            return emptyList();
        }

        return stream(input.split(","))
                .filter(not(String::isBlank))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(this::transformLocation)
                .distinct()
                .collect(toList());
    }

    private String transformLocation(String keyWord) {
        Matcher matcher = LOCATION_PATTERN.matcher(keyWord);
        if (matcher.find()) {
            return matcher.group(1) + "-" + matcher.group(2);
        }
        return keyWord;
    }

    private boolean isInvalidInput(String input) {
        return input == null || input.isEmpty() || input.matches("\\d+");
    }

}

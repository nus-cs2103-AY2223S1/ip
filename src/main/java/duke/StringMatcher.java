package duke;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class serves as a way to abstract the idea of getting a result
 * from a string.
 */
public class StringMatcher<T> {
    private Predicate<String> isMatch;
    private Function<String, T> getResult;

    /**
     * Constructs an object that calculates a particular case.
     *
     * @param isMatch Checks if this function handles this case.
     * @param getResult Function that calculates result.
     */
    public StringMatcher(Predicate<String> isMatch, Function<String, T> getResult) {
        assert isMatch != null;
        assert getResult != null;
        this.isMatch = isMatch;
        this.getResult = getResult;
    }

    public static Predicate<String> getCaseInsensitiveMatcher(String[] matches) {
        String[] copy = new String[matches.length];
        for (int i = 0; i < matches.length; i++) {
            copy[i] = matches[i].toLowerCase();
        }
        return s -> {
            String lowercase = s.toLowerCase();
            for (String candidate : copy) {
                if (lowercase.equals(candidate)) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * Checks if the string matches.
     * If it does, it would give the result.
     *
     * @param input String to check if it is for this command.
     * @return An Optional of class T if the string matches.
     */
    public Optional<T> run(String input) {
        assert input != null;
        if (isMatch.test(input)) {
            return Optional.of(getResult.apply(input));
        }
        return Optional.empty();
    }
}

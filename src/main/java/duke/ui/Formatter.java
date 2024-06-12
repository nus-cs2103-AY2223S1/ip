package duke.ui;

/**
 * A public interface that takes in a raw string and formats it.
 * For example, "Hi" -> "\n    Hi"
 */
public interface Formatter {
    /**
     * Takes in a raw string and formats it.
     *
     * @param input String that is raw.
     * @return String formatted and is about to be printed on some screen output.
     */
    String formatOutput(String input);
}

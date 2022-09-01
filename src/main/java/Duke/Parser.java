package duke;

/**
 * Parser class to assist in string parsing or manipulation
 */
public class Parser {

    public String replaceAll(String input, String regex) {
        return input.replaceAll(regex, "").trim();
    }
}

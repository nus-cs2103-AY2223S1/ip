public class CorruptedLineException extends DukeException {
    private static final String DESCRIPTION = "There was an error parsing this line: %n%s%n This line will be ignored!";

    CorruptedLineException(String line) {
        super(String.format(DESCRIPTION, line));
    }
}

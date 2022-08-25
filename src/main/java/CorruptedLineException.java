public class CorruptedLineException extends DukeException {
    private static final String DESCRIPTION = "There was an error parsing this line";

    CorruptedLineException() {
        super(DESCRIPTION);
    }
}

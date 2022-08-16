public class EmptyTimeException extends DukeException {
    private static final String DESCRIPTION = "%s requires a time/duration information, use <description> %s <duration> to indicate";

    EmptyTimeException(String commandName, String seperator) {
        super(String.format(DESCRIPTION, commandName, seperator));
    }
}

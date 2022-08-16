public class MissingEventDescriptionException extends DukeException {

    public MissingEventDescriptionException() {
        super("OOPS!!! The event description is missing the event duration\n " +
                "FORMAT: event <event description> /at <event duration> ");
    }
}

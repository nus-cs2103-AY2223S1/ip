public class InvalidEventException extends InvalidCommandException {
    public InvalidEventException() {
        super("Could not parse event. To create an event, "
                + "please use the format in this example: "
                + "event project meeting /at Mon 2-4pm");
    }
}

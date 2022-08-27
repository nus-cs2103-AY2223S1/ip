public class EventCommand extends TaskCommand {
    public static final String COMMAND_WORD = "event";

    public EventCommand(String command, String dateTime) throws UnexpectedDateTimeFormatException {
        super(new Event(command, dateTime));
    }
}

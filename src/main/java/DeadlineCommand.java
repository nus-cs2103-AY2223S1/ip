public class DeadlineCommand extends TaskCommand {
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(new Deadline(description, dateTime));
    }
}

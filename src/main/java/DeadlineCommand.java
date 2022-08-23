public class DeadlineCommand extends TaskCommand {

    public static final String COMMAND_NAME = "deadline";

    DeadlineCommand(Deadline newDeadline) {
        super(newDeadline);
    }
}
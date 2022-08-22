package duke.exception;

public class UnknownCommand extends DukeException {
    public UnknownCommand() {
        super("Unknown duke.commands.Command!");
    }
}

package duke.exceptions;

public class UnknownCommand extends DukeException {
    public UnknownCommand() {
        super("Unknown Command!");
    }
}

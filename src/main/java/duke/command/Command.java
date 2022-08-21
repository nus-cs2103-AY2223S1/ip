package duke.command;

import duke.DukeException;

public abstract class Command {
    public enum Commands {
        bye, list, help, mark, unmark, delete, todo, deadline, event, invalid;
    }

    public abstract void execute() throws DukeException;
    public abstract boolean isExit();

    public static Commands checkEnums(String command) {
        for (Commands e : Commands.values()) {
            if (e.name().equals(command)) {
                return e;
            }
        }
        return Commands.invalid;
    }
}

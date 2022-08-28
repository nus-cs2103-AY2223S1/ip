package duke.command;

import duke.task.Todo;

public class TodoCommand extends TaskCommand {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String command) {
        super(new Todo(command));
    }
}

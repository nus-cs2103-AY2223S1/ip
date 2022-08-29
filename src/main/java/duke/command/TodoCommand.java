package duke.command;

//import task
import duke.task.Todo;

public class TodoCommand extends TaskCommand {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String description) {
        super(new Todo(description));
    }
}

package duke.command;

import duke.task.ToDo;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(duke.TaskList taskList, String description) {
        super(taskList, "todo", description);
    }

    public void execute() {
        super.execute(new ToDo(super.description));
    }
}

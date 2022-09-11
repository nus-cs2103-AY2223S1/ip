package duke.command;

import duke.task.ToDo;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(duke.TaskList taskList, String description) {
        super(taskList, "todo", description);
    }

    public String execute() {
        super.execute(new ToDo(super.description));
        return String.format("todo %d", super.tasks.getSize());
    }
}

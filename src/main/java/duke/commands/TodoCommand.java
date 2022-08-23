package duke.commands;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description);
        taskList.addTask(todo);
        ui.showTaskAdded(todo);
        ui.showNumberOfTasks(taskList.numTasks());
    }
}

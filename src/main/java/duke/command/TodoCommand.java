package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTodo(description);
        int numOfTasks = taskList.getNumOfTasks();
        return ui.getAddTaskMessage(taskList.readTask(numOfTasks - 1), numOfTasks);
    }
}

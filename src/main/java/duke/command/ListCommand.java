package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_ID = "LIST";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String tasksLeft = String.format(
                "You have %d tasks in your todo list.", taskList.getTaskList().size());
        String result = taskList.listTasks();
        return String.format("%s\n%s", tasksLeft, result);
    }
}

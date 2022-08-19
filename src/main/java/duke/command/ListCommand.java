package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_ID = "LIST";
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(String.format(
                "You have %d tasks in your todo list.", taskList.getTaskList().size()));
        taskList.listTasks();
    }
}

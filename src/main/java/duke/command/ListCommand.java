package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a ListCommand which extends Command
 */
public class ListCommand extends Command {
    public static final String COMMAND_ID = "LIST";

    /**
     * Returns a string of the list all task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current list all task execution
     * @see duke.task.TaskList
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String tasksLeft = String.format(
                "You have %d tasks in your todo list.", taskList.getTaskList().size());
        String result = taskList.listTasks();
        return String.format("%s\n%s", tasksLeft, result);
    }
}

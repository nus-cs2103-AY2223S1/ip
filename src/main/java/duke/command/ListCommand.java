package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * a class to represent the list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Lists all the tasks in the list of tasks.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) {
        return Ui.getTaskListMessage(taskList);
    }
}

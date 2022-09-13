package duke.commands.tasks;

import java.util.Objects;

import duke.commands.BaseCommand;
import duke.tasklist.TaskList;

/**
 * Represents a Base Task.
 */
public abstract class BaseTaskCommand implements BaseCommand {
    protected TaskList taskList;

    /**
     * @param taskList
     *            Task List to be set
     */
    public void setTaskList(TaskList taskList) {
        assert Objects.nonNull(taskList);
        this.taskList = taskList;
    }
}

package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;
import roofus.task.ToDo;

/**
 * Represents a command action that adds a ToDo task to the TaskList
 * associated with the current instance of Roofus.
 */
public class ToDoCommand extends Command {
    private ToDo task;
    public ToDoCommand(ToDo task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String  execute(
            TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        return ui.addTask(task, taskList.length());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}

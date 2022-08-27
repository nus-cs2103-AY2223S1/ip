package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;
import roofus.task.Event;

/**
 * Represents a command action that adds an Event task to the TaskList
 * associated with the current instance of Roofus.
 */
public class EventCommand extends Command {
    private Event task;
    public EventCommand(Event task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
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

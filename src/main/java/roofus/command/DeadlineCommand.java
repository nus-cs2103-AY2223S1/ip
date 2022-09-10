package roofus.command;

import java.io.IOException;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;
import roofus.task.Deadline;

/**
 * Represents a command action that adds a Deadline task to the TaskList
 * associated with the current instance of Roofus.
 */
public class DeadlineCommand extends Command {
    private Deadline task;
    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return ui.addTask(task, taskList.length());
    }
}

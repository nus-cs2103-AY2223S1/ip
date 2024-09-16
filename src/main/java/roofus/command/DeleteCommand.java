package roofus.command;

import java.io.IOException;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;
import roofus.task.Task;

/**
 * Represents a command action that adds a task in the TaskList
 * associated with the current instance of Roofus.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                throw new RoofusException("Hey! It's not even in this list!");
            }
            try {
                storage.save(taskList);
            } catch (IOException err) {
                return ui.printErrMessage("file not saved");
            }
            Task deletedTask = taskList.delete(index);
            return ui.delete(deletedTask.toString(), taskList.length());
        } catch (RoofusException err) {
            return ui.printErrMessage(err.getMessage());
        }
    }
}

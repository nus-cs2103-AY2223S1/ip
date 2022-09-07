package roofus.command;

import java.io.IOException;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

/**
 * Represents a command action that sorts the TaskList instance
 * associated with the current instance of Roofus based on LocalDate attribute.
 */
public class SortCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        taskList.sort();
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return ui.list(taskList);
    }
}

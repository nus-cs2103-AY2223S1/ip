package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

/**
 * Represents a command action that finds all tasks that
 * contains a key specified by the user input in the TaskList
 * associated with the current instance of Roofus.
 */
public class FindCommand extends Command {
    private String key;
    public FindCommand(String key) {
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        return ui.filterList(taskList, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}

package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to find all tasks from a TaskList that matches the input description.
 */
public class FindCommand extends Command {

    private String description;

    /**
     * A constructor for a FindCommand.
     *
     * @param description the input description given by user
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     * @param ui an ui to handle user interactions
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.find(this.description, taskList);
    }
}

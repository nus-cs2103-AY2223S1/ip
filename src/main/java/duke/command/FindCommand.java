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
     * @param description The input description given by user.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String response = Ui.find(this.description, taskList);
        assert response != null : "Response should not be null";
        return response;
    }
}

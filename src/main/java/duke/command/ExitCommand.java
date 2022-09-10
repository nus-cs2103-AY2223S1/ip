package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command the exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        toggleIsExit();
        String response = Ui.sayBye();
        assert response != null : "response should not be null";
        return response;
    }
}

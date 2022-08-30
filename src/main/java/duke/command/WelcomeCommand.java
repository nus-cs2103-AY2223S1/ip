package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WelcomeCommand extends Command {
    /**
     * Executes the command to print the welcome greeting.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.printGreeting();
    }
}

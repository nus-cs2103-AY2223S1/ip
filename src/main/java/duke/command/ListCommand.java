package duke.command;

import duke.Ui;
import duke.processor.TaskList;

/**
 * Class to represent the command 'list'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ListCommand extends Command {
    /**
     * Prints the list of tasks that the user has inputted.
     *
     * @param tasks The list of tasks where the command is executed.
     */
    @Override
    public void execute(TaskList tasks) {
        Ui ui = new Ui();
        ui.list(tasks);
    }
}

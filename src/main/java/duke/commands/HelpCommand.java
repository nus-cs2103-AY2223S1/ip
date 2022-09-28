package duke.commands;

import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

/**
 * Represents the class that executes the help command
 */
public class HelpCommand extends Command {

    public HelpCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Shows the list of commands users can input
     *
     * @return response to be displayed
     */
    public String help() {
        return ui.printHelp();
    }
}

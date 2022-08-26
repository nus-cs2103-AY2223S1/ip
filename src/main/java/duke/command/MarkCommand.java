package duke.command;

import java.util.ArrayList;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the marking or unmarking of tasks.
 */
public class MarkCommand extends Command {

    /**
     * Initialises a MarkCommand.
     *
     * @param commandArgs An array of Strings containing information
     * pertaining to this specific mark command.
     * @param tasks An <code>ArrayList<Task></code>, containing the
     * current existing tasks in the programme.
     */
    public MarkCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Marks or unmarks a task to denote its completion.
     *
     * @return Returns true for the main Duke class to know to
     * continue asking for user input.
     */
    @Override
    public boolean performAction() {
        if (commandArgs[0].equals("mark")) {
            tasks.mark(Integer.parseInt(commandArgs[1]) - 1);
        } else if (this.commandArgs[0].equals("unmark")) {
            tasks.unmark(Integer.parseInt(commandArgs[1]) - 1);
        }

        return true;
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Class to represent the add command.
 */
public class AddCommand extends Command {

    /** User input for task details. */
    private String[] input;

    /**
     * Constructor for an AddCommand object.
     * @param input User input for task details.
     */
    public AddCommand(String[] input) {
        this.input = input;
    }

    /**
     * Calls the relevant functions to add task and print the result.
     */
    @Override
    public void executeCommand() throws DukeException {
        TaskList.getInstance().addTask(input);
    }
}

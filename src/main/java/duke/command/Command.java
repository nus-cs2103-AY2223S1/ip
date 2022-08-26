package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;


/**
 * An abstract class for all Commands.
 */
public abstract class Command {

    protected String[] commandArgs; // Stores the details of a user's input.
    protected TaskList tasks; // Stores the TaskList of a user.

    /**
     * Initialises a Command to store the details of the
     * user's input and the TaskList.
     */
    public Command(String[] commandArgs, TaskList tasks) {
        this.commandArgs = commandArgs;
        this.tasks = tasks;
    }

    /*
     * @return Returns true if the programme should continue seeking user input.
     * Returns false if the programme is to be terminated.
     */
    public abstract boolean performAction();
}


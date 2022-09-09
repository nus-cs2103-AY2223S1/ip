package duke.command;

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

    /**
     * Performs actions specific to the command, then returns a response message.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    public abstract String performAction();
}


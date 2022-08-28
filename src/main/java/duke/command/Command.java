package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class to encapsulate a command to be executed.
 */
public abstract class Command {
    private Task task;
    /**
     * Constructor for Command.
     *
     * @param task The task involved in this Command.
     */
    public Command(Task task) {
        this.task = task;
    }

    /**
     * Constructor for Command.
     * Does not have associated task.
     *
     */
    public Command() {
    }

    /**
     * Retrieve the task of this command.
     *
     * @return The task of this Command.
     */
    public Task getTask() {
        return this.task;
    }


    /**
     * Carry out the command instruction.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Return if command should cause exit from program.
     *
     * @return Whether the command causes exit from program.
     */
    public boolean isExit() {
        return false;
    }

}

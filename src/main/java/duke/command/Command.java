package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;


public abstract class Command {
    public Task task;
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

package duke.commands;

import duke.Storage;
import duke.DukeException;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Abstract class for all commands, representing a command that can be executed.
 */
public abstract class Command {
    public String instruction;

    /*
     * Constructs a Command object.
     * 
     * @param instruction The instruction to be executed.
     */
    public Command(String instruction) {
        this.instruction = instruction;
    }

    /*
     * Executes the command.
     * 
     * @param tasks The task list.
     * 
     * @param ui The user interface.
     * 
     * @param storage The storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Should not run");
    }

    /*
     * Returns true if the command is an exit command.
     * 
     * @return true if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}

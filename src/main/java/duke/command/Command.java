package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command class to execute operation.
 */
public abstract class Command {
    private String info;

    /**
     * Constructor of Command.
     *
     * @param info Type of command
     */
    public Command(String info) {
        this.info = info;
    }

    /**
     * @return Type of command
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Execute commands entered by users.
     *
     * @param ui Ui to show operation messages
     * @param taskList TaskList to execute command
     * @throws DukeException If invalid commands or arguments
     */
    public abstract void execute(Ui ui, TaskList taskList) throws DukeException;
}

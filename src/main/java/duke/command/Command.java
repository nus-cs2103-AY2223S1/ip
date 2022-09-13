package duke.command;

import duke.Storage;
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
     * Executes commands entered by users.
     *
     * @param ui Ui to show operation messages
     * @param taskList TaskList to execute command
     */
    public abstract String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException;
}

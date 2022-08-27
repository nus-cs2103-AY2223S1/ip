package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * The abstract class for describes what functions every handler must have.
 */
public abstract class CommandHandler {
    protected final String value;
    protected final String flag;
    protected final String additionalValue;

    /**
     * Constructor of CommandHandler that abstracts all the common fields of all the subclasses.
     * @param value parameter 1
     * @param flag parameter 2
     * @param additionalValue parameter 3
     * @throws DukeException error message.
     */
    public CommandHandler(String value, String flag, String additionalValue) throws DukeException {
        this.value = value;
        this.flag = flag;
        this.additionalValue = additionalValue;
        checkValid();
    }

    public abstract String handle(TaskList taskList) throws DukeException;

    /**
     * Checks the validity of the syntax of the command after being parsed by CommandParser.
     * @throws DukeException error message.
     */
    protected abstract void checkValid() throws DukeException;

}

package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public abstract class CommandHandler {
    protected final String value;
    protected final String flag;
    protected final String additionalValue;

    public CommandHandler(String value, String flag, String additionalValue) throws DukeException {
        this.value = value;
        this.flag = flag;
        this.additionalValue = additionalValue;
        checkValid();
    }

    abstract protected void checkValid() throws DukeException;

    abstract public String handle(TaskList taskList) throws DukeException;
}

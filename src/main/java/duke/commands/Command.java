package duke.commands;

import duke.core.DukeException;

public abstract class Command {
    protected final String invoker;

    public Command(String invoker) {
        this.invoker = invoker;
    }

    public String getInvoker() {
        return invoker;
    }

    public abstract String execute(String parameters) throws DukeException;

}

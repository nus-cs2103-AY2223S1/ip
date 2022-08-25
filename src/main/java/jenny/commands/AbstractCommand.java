package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;

import java.util.ArrayList;

public abstract class AbstractCommand {
    protected String arguments;

    public AbstractCommand(String arguments) {
        this.arguments = arguments;
    }

    public abstract void run(ArrayList<AbstractTask> tasks) throws JennyException;
}

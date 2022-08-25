package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;

import java.util.ArrayList;

public class InvalidCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = InvalidCommand.class.getSimpleName();
    private static final String COMMAND = "";
    private static final String ERROR_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";

    public InvalidCommand() {
        super(COMMAND);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_COMMAND);
    }
}

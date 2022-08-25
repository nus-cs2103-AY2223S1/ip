package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.TaskStorage;
import jenny.tasks.AbstractTask;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class ByeCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = ByeCommand.class.getSimpleName();
    public static final String COMMAND = "bye";

    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        UserInterface.exit();
    }
}

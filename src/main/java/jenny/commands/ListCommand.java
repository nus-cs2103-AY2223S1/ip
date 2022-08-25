package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class ListCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = ListCommand.class.getSimpleName();
    public static final String COMMAND = "list";
    private static final String ERROR_EMPTY_LIST = "There is nothing in your list to display.";

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        if (tasks.isEmpty()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_EMPTY_LIST);
        } else {
            UserInterface.print(tasks.toString());
        }
    }
}

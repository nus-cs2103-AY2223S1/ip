package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class DeleteCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = DeleteCommand.class.getSimpleName();
    public static final String COMMAND = "delete";
    private static final int OFFSET = -1;
    private static final String ERROR_EMPTY_LIST = "There is nothing in your list to delete.";
    private static final String ERROR_INVALID_NUMBER = "You have entered a invalid number for delete <number>.";
    private static final String ERROR_NO_RECORD = "No such record exists in your list.";
    private static final String DELETE_SUCCESS = "Got it. I've deleted this task:";

    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        if (tasks.isEmpty()) throw new JennyException(MESSAGE_SCOPE, ERROR_EMPTY_LIST);
        try {
            int index = Integer.parseInt(arguments) - OFFSET;
            AbstractTask task = tasks.remove(index);
            UserInterface.print(new String[]{
                    DELETE_SUCCESS,
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (NumberFormatException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_NO_RECORD);
        }
    }
}

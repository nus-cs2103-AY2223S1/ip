package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.util.Printer;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class MarkCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = MarkCommand.class.getSimpleName();
    public static final String COMMAND = "mark";
    private static final int OFFSET = -1;
    private static final String ERROR_INVALID_NUMBER = "You have entered a invalid number for mark <number>.";
    private static final String ERROR_NO_RECORD = "No such record exists in your list.";
    private static final String MARK_SUCCESS = "Nice! I've marked this task as done:";


    public MarkCommand(String arguments) {
        super(COMMAND);
        super.arguments = arguments;
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) {
        try {
            int index = Integer.parseInt(arguments) - OFFSET;
            AbstractTask task = tasks.get(index);
            task.markAsDone(true);
            UserInterface.print(new String[]{
                    MARK_SUCCESS,
                    "  " + task
            });
        } catch (NumberFormatException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_NO_RECORD);
        }
    }
}

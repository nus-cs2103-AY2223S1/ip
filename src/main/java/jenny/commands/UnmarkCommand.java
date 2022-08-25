package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.util.Printer;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class UnmarkCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = UnmarkCommand.class.getSimpleName();
    public static final String COMMAND = "unmark";
    private static final int OFFSET = -1;
    private static final String ERROR_INVALID_NUMBER = "You have entered a invalid number for unmark <number>.";
    private static final String ERROR_NO_RECORD = "No such record exists in your list.";
    private static final String UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";


    public UnmarkCommand(String arguments) {
        super(COMMAND);
        super.arguments = arguments;
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) {
        try {
            int index = Integer.parseInt(arguments) - OFFSET;
            AbstractTask task = tasks.get(index);
            task.markAsDone(false);
            UserInterface.print(new String[]{
                    UNMARK_SUCCESS,
                    "  " + task
            });
        } catch (NumberFormatException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_NO_RECORD);
        }
    }
}

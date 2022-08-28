package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;

import java.util.ArrayList;

/**
 * Displays the contents of the instance of {@link TaskList} to the user.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class ListCommand extends Command {
    private static final String MESSAGE_SCOPE = ListCommand.class.getSimpleName();
    public static final String COMMAND = "list";
    private static final String ERROR_EMPTY_LIST = "There is nothing in your list to display.";

    /**
     * Constructor for an instance of a new command.
     * Will initialise a new command.
     */
    public ListCommand() {
        super("");
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks   the instance of {@link TaskList} to run the command with.
     * @param ui      the instance of {@link Ui} to run the command with.
     * @param storage the instance of {@link Storage} to run the command with.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage<ArrayList<Task>> storage) throws JennyException {
        if (tasks.isEmpty()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_EMPTY_LIST);
        }
        ui.print(tasks.toString());
    }
}

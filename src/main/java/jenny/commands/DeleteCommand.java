package jenny.commands;

import java.util.ArrayList;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;


/**
 * Remove a task from the instance of {@link TaskList}.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private static final String MESSAGE_SCOPE = DeleteCommand.class.getSimpleName();
    private static final int OFFSET = -1;
    private static final String ERROR_EMPTY_LIST = "There is nothing in your list to delete.";
    private static final String DELETE_SUCCESS = "Got it. I've deleted this task:";

    /**
     * Constructor for an instance of a new command.
     * Will initialise a new command with the provided {@code arguments}.
     * Arguments must follow the format of {@code "number"},
     * where number is a valid integer.
     *
     * @param arguments valid command arguments defined.
     */
    public DeleteCommand(String arguments) {
        super(arguments);
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
        try {
            int index = Integer.parseInt(arguments) - OFFSET;
            Task task = tasks.remove(index);
            tasks.save(storage);
            ui.print(new String[]{
                DELETE_SUCCESS,
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (NumberFormatException | JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}

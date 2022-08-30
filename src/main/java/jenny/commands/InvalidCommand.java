package jenny.commands;

import java.util.ArrayList;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;


/**
 * Represents an invalid command sent by the user.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class InvalidCommand extends Command {
    private static final String MESSAGE_SCOPE = InvalidCommand.class.getSimpleName();
    private static final String ERROR_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";

    /**
     * Constructor for an instance of a new command.
     * Will initialise a new command.
     */
    public InvalidCommand() {
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
        throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_COMMAND);
    }
}

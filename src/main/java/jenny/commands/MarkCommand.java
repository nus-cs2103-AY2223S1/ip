package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;

import java.util.ArrayList;

/**
 * Mark a task from the instance of {@link TaskList} as complete.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class MarkCommand extends Command {
    private static final String MESSAGE_SCOPE = MarkCommand.class.getSimpleName();
    public static final String COMMAND = "mark";
    private static final int OFFSET = -1;
    private static final String MARK_SUCCESS = "Nice! I've marked this task as done:";

    /**
     * Constructor for an instance of a new command.
     * Will initialise a new command with the provided {@code arguments}.
     * Arguments must follow the format of {@code "number"},
     * where number is an integer.
     *
     * @param arguments valid command arguments defined.
     */
    public MarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     * @param tasks   the instance of {@link TaskList} to run the command with.
     * @param ui      the instance of {@link Ui} to run the command with.
     * @param storage the instance of {@link Storage} to run the command with.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage<ArrayList<Task>> storage) throws JennyException {
        try {
            int index = Integer.parseInt(arguments) - OFFSET;
            Task task = tasks.get(index);
            task.mark();
            tasks.save(storage);
            ui.print(new String[]{
                    MARK_SUCCESS,
                    "  " + task
            });
        } catch (NumberFormatException | JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}

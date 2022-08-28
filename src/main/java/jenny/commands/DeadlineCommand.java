package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.DeadlineTask;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;
import jenny.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Add a new deadline to the instance of {@link TaskList}.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineCommand extends Command {
    private static final String MESSAGE_SCOPE = DeadlineCommand.class.getSimpleName();
    public static final String COMMAND = "deadline";
    private static final Pattern VALID_DEADLINE_TASK = Pattern.compile(
            "(?<description>.+?)\\s/by\\s(?<dueDate>.+)");
    private static final String ERROR_INVALID_FORMAT =
            "You have entered an invalid format for deadline <description> /by <dueDate>.";
    private static final String ADD_DEADLINE_SUCCESS = "Got it. I've added this task:";

    /**
     * Constructor for an instance of a new command.
     * Will initialise a new command with the provided {@code arguments}.
     * Arguments must follow the format of {@code "description /by dueDate"},
     * where dueDate is formatted as {@code "yyyy-MM-dd"}.
     *
     * @param arguments valid command arguments defined.
     */
    public DeadlineCommand(String arguments) {
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
        Matcher matcher = VALID_DEADLINE_TASK.matcher(arguments);
        if (!matcher.matches()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_FORMAT);
        }
        try {
            String description = matcher.group("description").strip();
            String dueDate = matcher.group("dueDate").strip();
            LocalDate date = Validator.parseDate(dueDate);
            DeadlineTask task = new DeadlineTask(description, date);
            tasks.add(task);
            tasks.save(storage);
            ui.print(new String[]{
                    ADD_DEADLINE_SUCCESS,
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (IllegalStateException | IllegalArgumentException | JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}

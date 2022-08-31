package duke.commands;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Deadline;
import duke.utils.DukeValidator;

/**
 * Encapsulates a command for adding a {@link Deadline} task. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code deadline {taskDescription} /by {taskDeadline}}: To add a deadline task with the corresponding
 *         description and deadline.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class AddDeadlineTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_DESCRIPTION = AddDeadlineTaskCommand.COMMAND_WORD
            + " <description> /by <deadline>: Adds a Deadline task with the corresponding description and deadline";

    private static final String ERROR_INVALID_DEADLINE_TASK = "Use the 'deadline' command together with the "
            + "task description and deadline\nFor example: 'deadline return book /by 2022-12-02'";

    /**
     * Matches a non-empty description and a non-empty deadline, separated by a {@code '/by'} indicator.
     * <p>For example: {@code {taskDescription} /by {taskDeadline}}</p>
     */
    private static final Pattern MATCH_DEADLINE_TASK = Pattern.compile(
            "(?<taskDescription>.+?)\\s/by\\s(?<taskDeadline>.+)"
    );

    private final String arguments;

    /**
     * Creates a new instance of the Command handler for adding a {@link Deadline} task.
     *
     * @param arguments The arguments following the {@code 'deadline'} prefix supplied by the user from keyboard input
     */
    public AddDeadlineTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        Matcher matcher = AddDeadlineTaskCommand.MATCH_DEADLINE_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddDeadlineTaskCommand.ERROR_INVALID_DEADLINE_TASK);
        }

        String description = matcher.group("taskDescription").strip();
        String datelineString = matcher.group("taskDeadline").strip();
        LocalDate dateline = DukeValidator.parseDate(datelineString);

        return this.addTask(taskManager, () -> new Deadline(description, dateline));
    }
}

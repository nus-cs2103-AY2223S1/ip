package duke.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Task;
import duke.utils.DukeValidator;

/**
 * Encapsulates a command for listing the {@link Task Tasks} available. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code list}: To list all the tasks available.
 *     </li>
 *     <li>
 *         {@code list /on {date}}: To list all the tasks occurring on the corresponding date.
 *     </li>
 *     <li>
 *         {@code list /before {date}}: To list all the tasks occurring before the corresponding date.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class ListTasksCommand implements Command {
    public static final String COMMAND_WORD = "list";

    private static final String ERROR_UNKNOWN_OPTION = "Unknown option provided! Use either a '/on' or\n"
            + "'/before' command together with a date in order to\n"
            + "filter the tasks by their date!";

    /**
     * Matches a {@code '/on'} command followed by a (possibly invalid) date string. This is used to show the list of
     * tasks occurring on the specific date.
     */
    private static final Pattern MATCH_TASKS_ON = Pattern.compile("/on\\s(?<date>.+)");
    /**
     * Matches a {@code '/before'} command followed by a (possibly invalid) date string. This is used to show the list
     * of tasks occurring before the specific date.
     */
    private static final Pattern MATCH_TASKS_BEFORE = Pattern.compile("/before\\s(?<date>.+)");

    private final String arguments;

    /**
     * Creates a new instance of the Command handler for listing tasks.
     *
     * @param arguments The arguments following the {@code 'list'} prefix supplied by the user from keyboard input
     */
    public ListTasksCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        if (this.arguments.length() > 0) {
            Matcher matchTasksOn = ListTasksCommand.MATCH_TASKS_ON.matcher(this.arguments);
            if (matchTasksOn.matches()) {
                LocalDate date = DukeValidator.parseDate(matchTasksOn.group("date"));
                List<Task> filteredList = taskManager.list(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isEqual(date);
                        }
                );
                return TaskManager.display(filteredList);
            }
            Matcher matchTasksBefore = ListTasksCommand.MATCH_TASKS_BEFORE.matcher(this.arguments);
            if (matchTasksBefore.matches()) {
                LocalDate date = DukeValidator.parseDate(matchTasksBefore.group("date"));
                List<Task> filteredList = taskManager.list(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isBefore(date);
                        }
                );
                return TaskManager.display(filteredList);
            }
            throw new DukeException(ListTasksCommand.ERROR_UNKNOWN_OPTION);
        }

        return TaskManager.display(taskManager.list());
    }
}

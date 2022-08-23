package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Deadline;
import duke.utils.DukeValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String INVALID_DEADLINE_TASK_ERROR = "Use the 'deadline' command together with the " +
            "task description and deadline\nFor example: 'deadline return book /by 2022-12-02'";

    // Matches a non-empty description and a non-empty deadline, separated by a '/by' command
    // For example: (<description> /by <deadline>)
    private static final Pattern MATCH_DEADLINE_TASK = Pattern.compile("(?<taskDescription>.+?)\\s/by\\s(?<taskDeadline>.+)");

    private final String arguments;

    public AddDeadlineTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        Matcher matcher = AddDeadlineTaskCommand.MATCH_DEADLINE_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddDeadlineTaskCommand.INVALID_DEADLINE_TASK_ERROR);
        }

        String description = matcher.group("taskDescription").strip();
        String datelineString = matcher.group("taskDeadline").strip();
        LocalDate dateline = DukeValidator.parseDate(datelineString);
        uiManager.print(this.addTask(taskManager, () -> new Deadline(description, dateline)));
    }
}

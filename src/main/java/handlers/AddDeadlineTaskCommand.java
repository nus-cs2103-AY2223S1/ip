package handlers;

import exceptions.DukeException;
import models.Deadline;
import models.TaskManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineTaskCommand extends AddTaskCommand implements DukeCommand {
    private static final String INVALID_DEADLINE_TASK_ERROR = "Use the 'deadline' command together with the " +
            "task description and deadline\nFor example: 'deadline return book /by Sunday'";

    // Matches a non-empty description and a non-empty deadline, separated by a '/by' command
    // For example: (<description> /by <deadline>)
    private static final Pattern MATCH_DEADLINE_TASK = Pattern.compile("(.+?)\\s/by\\s(.+)");

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        Matcher matcher = AddDeadlineTaskCommand.MATCH_DEADLINE_TASK.matcher(arguments);
        if (!matcher.find()) {
            throw new DukeException(AddDeadlineTaskCommand.INVALID_DEADLINE_TASK_ERROR);
        }
        return this.addTask(taskManager, () -> new Deadline(matcher.group(1).strip(), matcher.group(2).strip()));
    }
}

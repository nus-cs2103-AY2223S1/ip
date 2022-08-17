package handlers;

import exceptions.DukeException;
import models.Event;
import models.TaskManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventTaskCommand extends AddTaskCommand implements DukeCommand {
    private static final String INVALID_EVENT_TASK_ERROR = "Use the 'event' command together with the " +
            "task description and date time\nFor example: 'event project meeting /at Mon 2-4pm'";

    // Matches a non-empty description and a non-empty datetime, separated by a '/at' command
    // For example: <description> /at <datetime>
    private static final Pattern MATCH_EVENT_TASK = Pattern.compile("(.+?)\\s/at\\s(.+)");

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        Matcher matcher = AddEventTaskCommand.MATCH_EVENT_TASK.matcher(arguments);
        if (!matcher.find()) {
            throw new DukeException(AddEventTaskCommand.INVALID_EVENT_TASK_ERROR);
        }
        return this.addTask(taskManager, () -> new Event(matcher.group(1).strip(), matcher.group(2).strip()));
    }
}

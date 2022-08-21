package commands;

import exceptions.DukeException;
import managers.TaskManager;
import managers.UiManager;
import models.task.Event;
import utils.DukeValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "event";
    private static final String INVALID_EVENT_TASK_ERROR = "Use the 'event' command together with the " +
            "task description and date\nFor example: 'event project meeting /at 2022-12-02'";

    // Matches a non-empty description and a non-empty date, separated by a '/at' command
    // For example: <description> /at <date>
    private static final Pattern MATCH_EVENT_TASK = Pattern.compile("(?<taskDescription>.+?)\\s/at\\s(?<taskDate>.+)");

    private final String arguments;

    public AddEventTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        Matcher matcher = AddEventTaskCommand.MATCH_EVENT_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddEventTaskCommand.INVALID_EVENT_TASK_ERROR);
        }
        String description = matcher.group("taskDescription").strip();
        String dateString = matcher.group("taskDate").strip();
        LocalDate date = DukeValidator.parseDate(dateString);
        uiManager.print(this.addTask(taskManager, () -> new Event(description, date)));
    }
}

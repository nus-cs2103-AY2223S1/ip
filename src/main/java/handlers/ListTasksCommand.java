package handlers;

import exceptions.DukeException;
import models.TaskManager;
import utils.DukeValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTasksCommand implements DukeCommand {
    private static final String ERROR_UNKNOWN_OPTION = "Unknown option provided! Use either a '/on' or\n" +
            "'/before' command together with a date in order to\n" +
            "filter the tasks by their date!";
    // Matches a '/on' command followed by a (possibly invalid) date string. This is used
    // to show the list of tasks occurring on the specific date.
    // For example: /on <date>
    private static final Pattern MATCH_TASKS_ON = Pattern.compile("/on\\s(?<date>.+)");
    // Matches a '/before' command followed by a (possibly invalid) date string. This is
    // used to show the list of tasks occurring before the specific date.
    // For example: /before <date>
    private static final Pattern MATCH_TASKS_BEFORE = Pattern.compile("/before\\s(?<date>.+)");

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        if (arguments.length() > 0) {
            Matcher matchTasksOn = ListTasksCommand.MATCH_TASKS_ON.matcher(arguments);
            if (matchTasksOn.find()) {
                LocalDate date = DukeValidator.parseDate(matchTasksOn.group("date"));
                return taskManager.where(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isEqual(date);
                        }
                ).toString();
            }
            Matcher matchTasksBefore = ListTasksCommand.MATCH_TASKS_BEFORE.matcher(arguments);
            if (matchTasksBefore.find()) {
                LocalDate date = DukeValidator.parseDate(matchTasksBefore.group("date"));
                return taskManager.where(
                        task -> {
                            LocalDate taskDate = task.getDate();
                            return taskDate != null && taskDate.isBefore(date);
                        }
                ).toString();
            }
            throw new DukeException(ListTasksCommand.ERROR_UNKNOWN_OPTION);
        }

        return taskManager.toString();
    }
}

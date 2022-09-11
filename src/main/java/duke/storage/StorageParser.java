package duke.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import duke.DateHandler;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a class which has custom methods to parse strings from .txt files
 */
public class StorageParser {
    static TaskList parseFile(List<String> lines) {
        List<Task> tasks = new ArrayList<>();
        lines.forEach((x) -> {
            try {
                tasks.add(formatTask(x));
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
        return new TaskList(tasks);
    }

    static Task formatTask(String rawTask) throws DukeException {
        String identifier = rawTask.substring(0, 1).toUpperCase(Locale.ROOT);

        String[] segments = rawTask.split("\\| ");

        switch (identifier) {
        case "T":
            return formatToDo(segments);

        case "D":
            return formatDeadline(segments);
        case "E":
            return formatEvent(segments);
        default:
            assert identifier.equals("T") || identifier.equals("D") || identifier.equals("E") : "Invalid identifier";
            throw new DukeException("Unable to parse this line");
        }
    }

    private static Task formatToDo(String[] segments) {
        boolean isMarked = false;
        StringBuilder sb = new StringBuilder();
        if (segments[1].contains("1")) {
            isMarked = true;
        }

        for (int i = 0; i < segments.length; i++) {
            if (i >= 2) {
                sb.append(segments[i]);
            }
        }

        Task formattedTask = new ToDo(sb.toString());

        if (isMarked) {
            formattedTask.markAsDone(true);
        }

        return formattedTask;
    }

    private static Task formatDeadline(String[] segments) {
        boolean isMarked = segments[1].contains("1");

        String deadlineDesc = "";
        StringBuilder deadlineBy = new StringBuilder();

        for (int i = 0; i < segments.length; i++) {
            if (i == 2) {
                deadlineDesc = segments[i];
            }
            if (i >= 3) {
                deadlineBy.append(segments[i]);
            }
        }

        LocalDateTime doneBy = DateHandler.parseDateString(deadlineBy.toString());
        Task formattedTask = new Deadline(deadlineDesc, doneBy);

        if (isMarked) {
            formattedTask.markAsDone(true);
        }

        return formattedTask;
    }

    private static Task formatEvent(String[] segments) {
        boolean isMarked = segments[1].contains("1");

        String eventDesc = "";
        StringBuilder eventBy = new StringBuilder();

        for (int i = 0; i < segments.length; i++) {
            if (i == 2) {
                eventDesc = segments[i];
            }
            if (i >= 3) {
                eventBy.append(segments[i]);
            }
        }

        LocalDateTime eventAt = DateHandler.parseDateString(eventBy.toString());
        Task formattedTask = new Event(eventDesc, eventAt);

        if (isMarked) {
            formattedTask.markAsDone(true);
        }

        return formattedTask;
    }
}

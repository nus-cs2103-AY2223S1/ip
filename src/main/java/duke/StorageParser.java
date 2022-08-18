package duke;

import duke.task.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StorageParser {
    static TaskList parseFile(List<String> lines) {
        List<Task> tasks = new ArrayList<>();
        lines.forEach((x) -> tasks.add(formatTask(x)));
        return new TaskList(tasks);
    }

    static Task formatTask(String rawTask) {
        String identifier = rawTask.substring(0,1).toUpperCase(Locale.ROOT);
        boolean marked = false;
        String[] segments = rawTask.split("\\| ");
        StringBuilder sb = new StringBuilder();
        Task formattedTask = new Task("", TaskType.TASK);

        switch (identifier) {
            case "T":
                if (segments[1].contains("1")) marked = true;

                for (int i = 0; i < segments.length; i++) {
                    if (i >= 2) {
                        sb.append(segments[i]);
                    }
                }

                formattedTask = new ToDo(sb.toString());

                if (marked) {
                    formattedTask.markAsDone(true);
                }

                return formattedTask;

            case "D":
                if (segments[1].contains("1")) marked = true;

                String deadlineDesc = "";
                StringBuilder deadlineBy = new StringBuilder();

                for (int i = 0; i < segments.length; i++) {
                    if (i == 2) deadlineDesc = segments[i];
                    if (i >= 3) deadlineBy.append(segments[i]);
                }

                LocalDateTime doneBy = DateHandler.parseDateString(deadlineBy.toString());
                formattedTask = new Deadline(deadlineDesc, doneBy);

                if (marked) formattedTask.markAsDone(true);

                return formattedTask;

            case "E":
                if (segments[1].contains("1")) marked = true;

                String eventDesc = "";
                StringBuilder eventBy = new StringBuilder();

                for (int i = 0; i < segments.length; i++) {
                    if (i == 2) eventDesc = segments[i];
                    if (i >= 3) eventBy.append(segments[i]);
                }

                LocalDateTime eventAt = DateHandler.parseDateString(eventBy.toString());
                formattedTask = new Event(eventDesc, eventAt);

                if (marked) formattedTask.markAsDone(true);

                return formattedTask;
        }
        return formattedTask;
    }
}

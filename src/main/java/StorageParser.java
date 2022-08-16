import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StorageParser {
    static Storage parseFile(List<String> lines) {
        List<Task> tasks = new ArrayList<>();
        lines.forEach((x) -> tasks.add(formatTask(x)));
        return new Storage(tasks);
    }

    static Task formatTask(String rawTask) {
        String identifier = rawTask.substring(0,1).toUpperCase(Locale.ROOT);
        boolean marked = false;
        String[] segments = rawTask.split("\\| ");
        StringBuilder sb = new StringBuilder();
        Task formattedTask = new Task("");

        switch (identifier) {
            case "T":
                if (rawTask.contains("1")) marked = true;

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
                if (rawTask.contains("1")) marked = true;

                String deadlineDesc = "";
                StringBuilder deadlineBy = new StringBuilder();

                for (int i = 0; i < segments.length; i++) {
                    if (i == 2) deadlineDesc = segments[i];
                    if (i >= 3) deadlineBy.append(segments[i]);
                }

                formattedTask = new Deadline(deadlineDesc, deadlineBy.toString());

                if (marked) formattedTask.markAsDone(true);

                return formattedTask;

            case "E":
                if (rawTask.contains("1")) marked = true;

                String eventDesc = "";
                StringBuilder eventBy = new StringBuilder();

                for (int i = 0; i < segments.length; i++) {
                    if (i == 2) eventDesc = segments[i];
                    if (i >= 3) eventBy.append(segments[i]);
                }

                formattedTask = new Event(eventDesc, eventBy.toString());

                if (marked) formattedTask.markAsDone(true);

                return formattedTask;
        }
        return formattedTask;
    }
}

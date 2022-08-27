package duke;

import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    public String toSaveData() {
        return this.stream().map(Task::toSaveData).collect(joining("\n"));
    }
}

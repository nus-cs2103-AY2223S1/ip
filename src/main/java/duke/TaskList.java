package duke;

import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    /**
     * Converts TaskList into save data string to be stored in a file.
     * @return the save data string
     */
    public String toSaveData() {
        return this.stream().map(Task::toSaveData).collect(joining("\n"));
    }
}

package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Returns the String representation of the TaskList for Storage.
     *
     * @return String representation of the TaskList for Storage.
     */
    public String data() {
        String acc = "";
        for (Task task : this) {
            acc += task.data() + "\n";
        }
        return acc;
    }
}

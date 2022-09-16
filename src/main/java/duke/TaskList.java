package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
<<<<<<< Updated upstream
    public String data() {
=======
    /**
     * Returns the String representation of the TaskList for Storage.
     *
     * @return String representation of the TaskList for Storage.
     */
    public String toData() {
>>>>>>> Stashed changes
        String acc = "";
        for (Task task : this) {
            acc += task.toData() + "\n";
        }
        return acc;
    }
}

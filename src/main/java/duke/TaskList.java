package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public String data() {
        String acc = "";
        for (Task task : this) {
            acc += task.data() + "\n";
        }
        return acc;
    }
}

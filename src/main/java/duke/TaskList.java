package duke;

import java.util.ArrayList;
import duke.tasks.*;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void append(Task t) {
        this.taskList.add(t);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public int length() {
        return this.taskList.size();
    }

    public Task index(int i) {
        return this.taskList.get(i);
    }
}

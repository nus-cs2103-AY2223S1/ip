package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    public final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

}

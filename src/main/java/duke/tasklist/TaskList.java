package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

}

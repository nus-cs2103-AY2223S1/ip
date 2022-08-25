package duke.storage;
import java.util.ArrayList;

import duke.task.Task;
public class TaskRecords {
    private final ArrayList<Task> lst;

    public TaskRecords() {
        this.lst = new ArrayList<Task>();
    }

    public void addProcess(Task task) {
        if (!this.lst.contains(task)) {
            this.lst.add(task);
        } else {
            this.lst.set(lst.indexOf(task), task);
        }
    }

    public Task delete(int idx) {
        Task currTask = this.lst.get(idx);
        this.lst.remove(idx);
        return currTask;
    }

    public Task get(int idx) {
        return this.lst.get(idx);
    }

    public ArrayList<Task> getList() {
        return this.lst;
    }
}

package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;
    public TaskList(Storage storage){
        this.arr = new ArrayList<Task>();
        storage.arr = this.arr;
    }
    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public void add(Task task) {
        this.arr.add(task);
    }
    public Task delete(int i) throws IndexOutOfBoundsException {
        return this.arr.remove(i);
    }
    public int getSize() {
        return this.arr.size();
    }
    public Task getTask(int i) {
        return this.arr.get(i);
    }
    public ArrayList<Task> getArr() {
        return this.arr;
    }
}

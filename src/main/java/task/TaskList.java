package task;

import exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    protected Task task;
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) throws DukeException {
            this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void update() {

    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void forEach() {
        taskList.forEach(n -> System.out.println((taskList.indexOf(n) + 1) + "."
                + n.toString()));
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> toArray() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }
}

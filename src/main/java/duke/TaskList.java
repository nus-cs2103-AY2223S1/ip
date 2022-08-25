package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void remove(int num) {
        tasks.remove(num);
    }
}

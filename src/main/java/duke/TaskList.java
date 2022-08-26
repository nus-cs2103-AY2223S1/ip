package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int idx) {
        tasks.remove(idx);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> find(String s) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(s)) {
                temp.add(task);
            }
        }
        return temp;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}

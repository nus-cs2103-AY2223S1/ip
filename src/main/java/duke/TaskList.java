package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int taskNumber) {
        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("Ok! I have removed the following task:\n" + t.toString());
    }

    public void addTask(Task t) {
        tasks.add(t);
        System.out.println("Ok! I have added the following task:\n" + t.toString());
    }
}

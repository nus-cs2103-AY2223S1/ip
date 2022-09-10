package main;

import java.util.ArrayList;

import task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<Task>();
    }

    TaskList(TaskList tasklist) {
        this.tasks = new ArrayList<Task>();
        this.tasks.addAll(tasklist.tasks); //deep copy ArrayList
    }

    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String output = "";
        int count = 1;
        if (tasks.size() == 0) {
            return "No tasks! Yay!";
        }
        for (Task task : tasks) {
            output += String.valueOf(count) + ". " + task + "\n";
            count += 1;
        }
        return output;
    }
}
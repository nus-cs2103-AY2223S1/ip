package ted.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ted.exception.TedException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public String list() {
        String listOfTasks = "Your tasklist:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int bulletPoint = i + 1;
            listOfTasks = listOfTasks + bulletPoint + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;
    }

    public void writeToFile(FileWriter fw) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toFileString());
        }
    }

    public String markTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        tasks.get(i - 1).markDone();
        return tasks.get(i - 1).toString();
    }

    public String unmarkTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        tasks.get(i - 1).unmarkDone();
        return tasks.get(i - 1).toString();
    }

    public String addTask(Task t) {
        tasks.add(t);
        return t.toString();
    }

    public String deleteTask(int i) throws TedException {
        if (i - 1 < 0 || i > tasks.size()) {
            throw new TedException("Oh no, there's no such task T_T\n");
        }

        String task = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        return task;
    }
}

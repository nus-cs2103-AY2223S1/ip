package duke;

import java.awt.*;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    public static String printTaskList(TaskList tl) {
        String taskRecords = "";
        if (tl.size() == 0) {
            taskRecords = "No items found in TaskList";
        }
        for (int i = 0; i < tl.size(); i++) {
            String taskRecord="";
            if (i == tl.size() - 1) {
                taskRecord = String.format("%d.%s", i + 1, tl.get(i));
            } else {
                taskRecord = String.format("%d.%s\n", i + 1, tl.get(i));
            }
            taskRecords += taskRecord;
        }
        return taskRecords;
    }

    public int size() {
        return this.allTasks.size();
    }

    public Task get(int index) {
        return this.allTasks.get(index);
    }

    public void add(Task t) {
        this.allTasks.add(t);
    }

    public Task remove(int index) {
        return this.allTasks.remove(index);
    }

    public TaskList find(String word) {
        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < this.allTasks.size(); i++) {
            if (this.allTasks.get(i).toString().contains(word)) {
                newList.add(this.allTasks.get(i));
            }
        }
        TaskList newTaskList = new TaskList(newList);
        return newTaskList;
    }
}

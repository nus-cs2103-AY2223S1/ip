package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void addTask(Task t) {
        this.tasks.add(t);
    }
    
    public Task getTask(int i) {
        return this.tasks.get(i);
    }
    
    public void removeTask(int i) {
        this.tasks.remove(i);
    }
    
    public int getTaskListSize() {
        return this.tasks.size();
    }
    
    public String listTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(" ").append(tasks.get(i)).append(i != tasks.size() - 1 ? "\n" : "");
        }
        return list.toString();
    }
}

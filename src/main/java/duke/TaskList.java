package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                result.add(t);
            }
        }
        return result;

    }

}

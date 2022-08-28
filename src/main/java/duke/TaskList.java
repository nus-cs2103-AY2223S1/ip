package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> addTask(Task t) {
        tasks.add(t);
        return tasks;
    }

    public Task deleteTask(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    /**
     * Returns a list of tasks from the task list that contain the given keyword(s).
     *
     * @param keyword The given keyword(s).
     * @return The list of matching tasks.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.get(i);
            String s = t.getDescription();
            if (s.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> markTask(Task t, boolean b) {
        t.setMarked(b);
        return tasks;
    }
}

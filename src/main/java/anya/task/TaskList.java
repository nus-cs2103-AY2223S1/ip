package anya.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Instance methods
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTaskFromIndex(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTaskFromIndex(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Gets a filtered ArrayList of tasks from TaskList that contains the keyword.
     *
     * @param keyword The word that the task must contain.
     * @return a filtered TaskList where each task contains the keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.nameContains(keyword)) {
                filteredTasks.add(task);
            }
        }

        return new TaskList(filteredTasks);
    }
}

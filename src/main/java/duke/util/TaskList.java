package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    // Class Fields
    public final ArrayList<Task> tasks;

    // Constructor
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param taskNum the index of task to be deleted
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum - 1);
    }

    /**
     * Prints list of tasks in the Checklist.
     * @return the list of tasks in the Checklist in String format
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            Task curr = tasks.get(i);
            if (i == tasks.size() - 1) {
                output.append(i + 1).append(". ").append(curr.toString());
            } else {
                output.append(i + 1).append(". ").append(curr.toString()).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Prints the entire string of a task in the Checklist. (eg. [T][] read book)
     * @param idx index of the task in the Checklist
     * @return the status string of the task in the Checklist in String format
     */
    public String printTaskStatus(int idx) {
        Task curr = tasks.get(idx);
        return curr.toString();
    }
}
package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    // Class Fields
    public final ArrayList<Task> tasklist;

    // Constructor
    public TaskList() {
        tasklist = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasklist.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param taskNum the index of task to be deleted
     */
    public void deleteTask(int taskNum) {
        tasklist.remove(taskNum - 1);
    }

    /**
     * Prints list of tasks in the Checklist.
     * @return the list of tasks in the Checklist in String format
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasklist.size(); ++i) {
            Task curr = tasklist.get(i);
            if (i == tasklist.size() - 1) {
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
        Task curr = tasklist.get(idx);
        return curr.toString();
    }
}
package duke.util;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;

public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Display all stored tasks
     */
    public void displayList() {
        System.out.println("Here are the tasks in your list.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Add task to tasks list
     * 
     * @param task Task to be stored in task list
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Gotcha! I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Add task to task list without any user messages (Used when loading data from
     * file)
     * 
     * @param task Task to be pushed into task list
     */
    public void pushTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes specified task from tasks list
     * 
     * @param taskIndex Index of task to be removed
     * @throws DukeException if given index is out of bounds
     */
    public void removeTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        }
        Task task = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
    }

    /**
     * Changes the completed status of specified task
     * 
     * @param taskIndex Index of task to be changed
     * @param isDone    true if task is completed, false otherwise
     * @throws DukeException if given index is out of bounds
     */
    public void changeTaskStatus(int taskIndex, boolean isDone) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        }
        if (isDone) {
            tasks.get(taskIndex).markAsDone();
        } else {
            tasks.get(taskIndex).markAsNotDone();
        }
    }

    /**
     * Return the string representation of all tasks in list to be stored in local
     * file
     * 
     * @return String representation of all tasks in list
     */
    public String getFileText() {
        String fileString = "";
        for (Task task : tasks) {
            fileString += task.getFileString() + "\n";
        }
        return fileString;
    }
}

package seedu.duke.task;

import seedu.duke.ui.Style;

import java.util.ArrayList;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    /* All the tasks. */
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList object with an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with a populated ArrayList.
     *
     * @param tasks An ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the Task at that specified index based on the output of ListCommand.execute(...).
     *
     * @param index The index of the task item based on the output of the ListCommand.execute(...).
     * @return A Task at that specified location.
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the added task.
     *
     * @param task The task to be added to the ArrayList of tasks.
     * @return The Task added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes the task at the specified location given by index.
     *
     * @param index The index of the task based on the output of ListCommand.execute(...).
     * @return The Task after it has been deleted.
     */
    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Deletes the task at the specified location given by index after it has been marked.
     *
     * @param index The index of the task based on the output of ListCommand.execute(...).
     * @return The Task after it has been marked.
     */
    public Task markTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes the task at the specified location given by index after it has been unmarked.
     *
     * @param index The index of the task based on the output of ListCommand.execute(...).
     * @return The Task after it has been unmarked.
     */
    public Task unmarkTask(int index) {
        Task task = getTask(index);
        task.unmark();
        return task;
    }

    /**
     * Prints out all the tasks stored in ArrayList
     */
    public String listTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Style.INDENTATION + (i + 1) + "." + getTask(i + 1));
            output += Style.INDENTATION + (i + 1) + "." + getTask(i + 1) + "\n";
        }
        return output;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


    public int getNumberOfTasks() {
        return tasks.size();
    }


    /**
     * Displays the matching tasks based on the keyword.
     *
     * @param keyword Specifies the keyword to match the tasks to.
     */
    public String displayMatchingTasks(String keyword) {
        String text = "";
        int j = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = getTask(i + 1);
            if (task.toString().contains(keyword)) {
                System.out.println(Style.INDENTATION + (j + 1) + "." + task);
                text += Style.INDENTATION + (j + 1) + "." + task + "\n";
                j++;
            }
        }
        return text;
    }
}

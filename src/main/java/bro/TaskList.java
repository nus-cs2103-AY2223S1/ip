package bro;

import java.io.IOException;
import java.util.ArrayList;

import bro.task.Task;

/**
 * TaskList class.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Ui ui = new Ui();

    /**
     * Constructor that initialises tasks variable.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor that initialises tasks variable with the given task.
     */
    public TaskList(ArrayList<Task> task) {
        this.tasks = task;
    }
    /**
     * Returns the size of task.
     * @return The TaskList size.
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Returns the task in the particular index.
     * @param index The index of the task.
     * @return The task at the particular index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Lists all the task in the ArrayList tasks.
     * @return The list of the tasks.
     */
    public String listAll() {
        String result = "Here are the tasks:" + "\n";
        int count = 1;
        if (this.tasks.size() == 0) {
            return "U R FREE! No tasks left.";
        }
        for (Task t : this.tasks) {
            result += count + "." + t.toString() + "\n";
            count++;
        }
        return result;
    }

    /**
     * Marks the task as done by setting isDone boolean to true.
     *
     * @param n   Index of the task to be marked.
     * @param sto Storage location of the file.
     */
    public String markTask(int n, Storage sto) {
        String result = "";
        this.tasks.get(n - 1).markAsDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.markUi(this, n);
        result += ui.listSize(this);
        return result;
    }

    /**
     * Unmarks the task as done by setting isDone boolean to false.
     *
     * @param n   Index of the task to be unmarked.
     * @param sto Storage location of the file.
     */
    public String unmarkTask(int n, Storage sto) {
        String result = "";
        this.tasks.get(n - 1).markAsNotDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.unmarkUi(this, n);
        result += ui.listSize(this);
        return result;
    }

    /**
     * Adds todo task to the list of the tasks.
     *
     * @param t   Task to be added
     * @param sto Storage location of the file.
     */
    public String todoTask(Task t, Storage sto) {
        String result = "";
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.printAdd(t);
        result += ui.listSize(this);
        return result;
    }

    /**
     * Adds deadline task to the list of the tasks.
     *
     * @param t   Task to be added
     * @param sto Storage location of the file.
     */
    public String deadlineTask(Task t, Storage sto) {
        String result = "";
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.printAdd(t);
        result += ui.listSize(this);
        return result;
    }

    /**
     * Adds event task to the list of the tasks.
     *
     * @param t   Task to be added
     * @param sto Storage location of the file.
     */
    public String eventTask(Task t, Storage sto) {
        String result = "";
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.printAdd(t);
        result += ui.listSize(this);
        return result;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param n   Index of the task to be deleted.
     * @param sto Storage location of the file.
     */
    public String deleteTask(int n, Storage sto) {
        String result = "";
        result += ui.deleteUi(this, n);
        tasks.remove(n - 1);
        try {
            sto.modifyTaskFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result += ui.listSize(this);
        return result;
    }

    /**
     * Returns tasks with the given keyword.
     *
     * @param keyword The word which has to be found in the file.
     */
    public String findTask(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        tasks.stream().filter(t -> t.toString().contains(keyword)).forEach(found::add);
        return ui.findUi(found);
    }
}

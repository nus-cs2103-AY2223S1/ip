package byu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidIndexException;
import task.Task;

/**
 * Represents a list containing tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;
    private Ui ui;

    /**
     * Creates a ToDoList that stores tasks.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.numOfTasks += 1;
        String output = String.format("added: %s\n", task.toString());
        if (this.numOfTasks == 1) {
            output += String.format("Now you have %d task in the list.\n", this.numOfTasks);
        } else {
            output += String.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        }
        this.ui.setOutput(output);
    }

    /**
     * Prints all the tasks in the list.
     */
    public void list() {
        String output = "These are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        this.ui.setOutput(output);
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the Task to be marked.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void mark(int i) throws InvalidIndexException {
        if (i < 0 || i > numOfTasks) {
            throw new InvalidIndexException();
        }
        Task t = tasks.get(i - 1);
        t.setDone(true);
        String output = String.format("Nice! I've marked this task as done:\n%s\n", t);
        this.ui.setOutput(output);
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the Task to be unmarked.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void unmark(int i) throws InvalidIndexException {
        if (i < 0 || i > numOfTasks) {
            throw new InvalidIndexException();
        }
        Task t = tasks.get(i - 1);
        t.setDone(false);
        String output = String.format("OK, I've marked this task as not done yet:\n%s\n", t);
        this.ui.setOutput(output);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i the index of the Task to be deleted.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void delete(int i) throws InvalidIndexException {
        if (i < 0 || i > numOfTasks) {
            throw new InvalidIndexException();
        }
        Task t = tasks.get(i - 1);
        this.tasks.remove(i - 1);
        this.numOfTasks -= 1;
        String output = String.format("Alright! I've removed this task:\n%s\n", t);
        output += String.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        this.ui.setOutput(output);
    }

    /**
     * Updates the file.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter("./Duke.txt");
            for (int i = 0; i < numOfTasks; i++) {
                Task t = tasks.get(i);
                t.write(fw);
            }
            fw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public void find(String s) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(s)) {
                output += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
            }
        }
        this.ui.setOutput(output);
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

}

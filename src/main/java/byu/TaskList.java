package byu;

import exceptions.InvalidIndexException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a list containing tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;

    /**
     * Creates a ToDoList that stores tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.numOfTasks += 1;
        System.out.format("added: %s\n", task.toString());
        if (this.numOfTasks == 1) {
            System.out.format("Now you have %d task in the list.\n", this.numOfTasks);
        } else {
            System.out.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        }
    }

    /**
     * Prints all the tasks in the list.
     */
    public void list() {
        System.out.println("These are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the Task to be marked.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void mark(int i) throws InvalidIndexException  {
        if (i < 0 || i > numOfTasks) {
            throw new InvalidIndexException();
        }
        Task t = tasks.get(i - 1);
        t.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + t);
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
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
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
        System.out.println("Alright! I've removed this task:\n" + t);
        System.out.format("Now you have %d tasks in the list.\n", this.numOfTasks);
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
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(s)) {
                System.out.format("%d. %s\n", i + 1, tasks.get(i).toString());
            }
        }
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

}

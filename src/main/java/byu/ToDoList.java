package byu;

import exceptions.InvalidIndex;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a list containing tasks.
 */
public class ToDoList {

    private final ArrayList<Task> list;
    private int numOfTasks = 0;

    /**
     * Creates a ToDoList that stores tasks.
     * @return a ToDoList.
     */
    public ToDoList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
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
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the Task to be marked.
     * @throws InvalidIndex if i > number of tasks in the list.
     */
    public void mark(int i) throws InvalidIndex  {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        t.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the Task to be unmarked.
     * @throws InvalidIndex if i > number of tasks in the list.
     */
    public void unmark(int i) throws InvalidIndex {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        t.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i the index of the Task to be deleted.
     * @throws InvalidIndex if i > number of tasks in the list.
     */
    public void delete(int i) throws InvalidIndex {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        this.list.remove(i - 1);
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
                Task t = list.get(i);
                t.write(fw);
            }
            fw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

}

package byu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import exceptions.InvalidIndexException;
import task.Task;

/**
 * Represents a list containing tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int numOfTasks = 0;
    private final Ui ui;

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
        assert tasks.size() >= 0 : "size of list should be non-negative";
        String output = "These are the tasks in your list:\n";
        IntStream intStream = IntStream.rangeClosed(1, numOfTasks);
        Stream<String> stringStream = intStream.mapToObj(
                i -> String.format("%d. %s\n", i, this.getTask(i).toString()));
        String result = stringStream.reduce("", (x, y) -> x + y);
        output += result;
        this.ui.setOutput(output);
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the Task to be marked.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void mark(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        task.setDone(true);
        String output = String.format("Nice! I've marked this task as done:\n%s\n", task);
        this.ui.setOutput(output);
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the Task to be unmarked.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void unmark(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        task.setDone(false);
        String output = String.format("OK, I've marked this task as not done yet:\n%s\n", task);
        this.ui.setOutput(output);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i the index of the Task to be deleted.
     * @throws InvalidIndexException if i > number of tasks in the list.
     */
    public void delete(int i) throws InvalidIndexException {
        int arrayIndex = getValidArrayIndex(i);
        Task task = tasks.get(arrayIndex);
        this.tasks.remove(arrayIndex);
        this.numOfTasks -= 1;
        String output = String.format("Alright! I've removed this task:\n%s\n", task);
        output += String.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        this.ui.setOutput(output);
    }

    private int getValidArrayIndex(int i) throws InvalidIndexException {
        boolean isNegative = i < 0;
        boolean isMoreThanLength = i > numOfTasks;
        if (isNegative || isMoreThanLength) {
            throw new InvalidIndexException();
        }
        return i - 1;
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

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

}

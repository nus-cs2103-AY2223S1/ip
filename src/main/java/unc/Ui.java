package unc;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;

import java.util.Scanner;

/**
 * Handles input and output.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;

    /**
     * Constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a preset welcome message.
     */
    public void showWelcome() {

        System.out.println("Hello from\n" + LOGO);
        System.out.println("Welcome to UNC\n");
    }

    /**
     * Stops reading inputs.
     * Prints a preset goodbye message.
     */
    public void goodbye() {
        scanner.close();
        System.out.println("Bye");

    }

    /**
     * Reads in user input line by line.
     *
     * @return A line of input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the entire list.
     * One line for each task.
     *
     * @param taskList List.
     */
    public void displayList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param todo Added task.
     */
    public void addTodo (TaskList taskList, Todo todo) {
        System.out.println("added: \n " + todo + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param event Added task.
     */
    public void addEvent (TaskList taskList, Event event) {
        System.out.println("added: \n " + event + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param deadline Added task.
     */
    public void addDeadline (TaskList taskList, Deadline deadline) {
        System.out.println("added: \n " + deadline + "\nNow you have " + taskList.size() +
                " tasks on the list.");
    }

    /**
     * Prints out the task that was marked.
     *
     * @param taskList List.
     * @param index The index of newly marked task.
     */
    public void mark (TaskList taskList, int index) {
        System.out.println("Marked as done: \n" + taskList.get(index));
    }

    /**
     * Prints out the task that was unmarked.
     *
     * @param taskList List.
     * @param index The index of newly unmarked task.
     */
    public void unmark (TaskList taskList, int index) {
        System.out.println("Marked as not done: \n" + taskList.get(index));
    }

    /**
     * Prints out the task that was removed.
     *
     * @param task The recently removed task.
     * @param size The new size of list.
     */
    public void delete (Task task, int size) {
        System.out.println("Deleted: \n" + task + "\nNow you have " + size +
                " tasks on the list.");
    }

    public void displayFoundList(TaskList taskList) {
        displayList(taskList);
    }
}

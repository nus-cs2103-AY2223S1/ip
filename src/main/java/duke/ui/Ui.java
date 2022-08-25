package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Part of the chatbot that display messages to the user.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for the UI Object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String GetUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Greet the user when the chatbot is active.
     */
    public void displayHello() {
        displaySeparator();
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
        displaySeparator();
    }

    public void displayLoading() {
        System.out.println("Loading previously saved tasks");
    }

    /**
     * Inform the user that the previously saved tasks has been loaded to the chatbot.
     */
    public void displayLoadingSuccess() {
        System.out.println("Successfully loaded");
    }

    /**
     * Inform the user that there was no previously saved tasks.
     */
    public void displayLoadingError() {
        System.out.println("OOPS!!! Couldn't find any saved tasks");
    }

    /**
     * Display all the tasks of the specific task list.
     * @param tasks The specific arraylist that contains all the tasks to be displayed.
     */
    public void displayTaskList(ArrayList<Task> tasks) {
        int size = tasks.size();
        displaySeparator();
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).taskInfo());
        }
        displaySeparator();
    }

    /**
     * Inform the user that the task that want to mark has been completed.
     */
    public void displayMarkTask(Task task) {
        displaySeparator();
        System.out.println("Nice! I've marked this task as done:\n " + task.taskInfo());
        displaySeparator();
    }

    /**
     * Inform the user that the task that want to mark as not done has been completed.
     */
    public void displayUnmarkTask(Task task) {
        displaySeparator();
        System.out.println("Ok, I've marked this task as not done yet:\n " + task.taskInfo());
        displaySeparator();
    }

    /**
     * Inform the user that the task that want to add has been completed.
     */
    public void displayAddTask(Task task) {
        displaySeparator();
        System.out.println("Got it. I've added this task:\n " + task.taskInfo());

    }

    /**
     * Inform the user that the task that want to delete has been completed.
     */
    public void displayDeleteTask(Task task) {
        displaySeparator();
        System.out.println("Noted. I've removed this task:\n " + task.taskInfo());
    }

    /**
     * Display a goodbye message when the user exits the chatbot.
     */
    public void displayBye() {
        displaySeparator();
        System.out.println("Bye. Hope to see you again soon!");
        displaySeparator();
    }

    /**
     * Inform the user the current number of tasks in the task list.
     * @param taskSize The current size of the task list.
     */
    public void displayNumOfTasks(int taskSize) {
        if (taskSize == 1) {
            System.out.println("Now you have " + taskSize + " task in the list.");
        } else {
            System.out.println("Now you have " + taskSize + " tasks in the list.");
        }
    }

    /**
     * Inform the user that the task they are trying to access to perform action does not exist.
     */
    public void displayInvalidTaskIndex() {
        displaySeparator();
        System.out.println("There is no task at this index");
        displaySeparator();
    }

    public void displaySeparator() {
        System.out.println("--------------------------------");
    }
}

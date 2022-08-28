package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner scanner;
    private final String line = "--------------------------------------------------------------------------------\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line + "Hello! I'm Duke");
        System.out.println("What tasks do you have to do?\n" + line);
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye! See you soon!");
    }

    /**
     * Prints list of tasks.
     *
     * @param tasks Arraylists containing tasks to be printed.
     */
    public void printList(ArrayList<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i).toString());
        }
        drawLine();
    }

    /**
     * Prints task as done.
     *
     * @param task Task to be printed as done.
     */
    public void printDone(Task task) {
        drawLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
        drawLine();
    }

    /**
     * Prints task as undone.
     *
     * @param task Task to be printed as undone.
     */
    public void printUndone(Task task) {
        drawLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
        drawLine();
    }

    /**
     * Prints todo task.
     *
     * @param task Todo task to be added.
     */
    public void printTodo(Task task) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    /**
     * Prints task to be deleted.
     *
     * @param task Task to be deleted.
     */
    public void printDelete(Task task) {
        drawLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
    }

    /**
     * Prints number of tasks left in list.
     *
     * @param num Number of tasks left in list.
     */
    public void printTasksLeft(int num) {
        System.out.println("Now you have " + num + " tasks in the list.");
        drawLine();
    }

    /**
     * Prints tasks that matches keyword
     *
     * @param matchedTasks Arraylist of tasks that matches
     */
    public void printFind(ArrayList<Task> matchedTasks) {
        drawLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int j = 0; j < matchedTasks.size(); j++) {
            int num = j + 1;
            System.out.println(num + ". " + matchedTasks.get(j).toString());
        }
        drawLine();
    }

    /**
     * Prints loading error.
     */
    public void printLoadingError() {
        System.out.println("Unable to load file");
    }

    /**
     * Prints horizontal line.
     */
    public void drawLine() {
        System.out.println(line);
    }

}

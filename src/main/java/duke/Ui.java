package duke;

import java.util.Scanner;

/**
 * Represents the UI component of Duke.
 */
public class Ui {
    public Scanner sc = new Scanner(System.in);

    /**
     * Prints a String to the UI.
     *
     * @param string String to print.
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Prints a greeting message to the UI.
     */
    public void greet() {
        print("Hello! I'm duke.Duke");
        print("What can I do for you?");
    }

    /**
     * Reads a line of user input and returns it.
     *
     * @return A line of user input that is read.
     */
    public String read() {
        return sc.nextLine();
    }

    /**
     * Prints a response from adding a Task in Duke to the UI.
     *
     * @param task Task added in Duke.
     */
    public void addTask(Task task) {
        print("Got it. I've added this task:");
        print(task.toString());
    }

    /**
     * Prints all tasks in the TaskList to the UI.
     *
     * @param tasks List of tasks to print.
     */
    public void printTasks(TaskList tasks) {
        print("Here are the tasks in your list:");
        for (int a = 0; a < tasks.size(); a++) {
            print((a + 1) + ". " + tasks.get(a));
        }
    }

    /**
     * Prints a response from marking a Task as done in Duke to the UI.
     *
     * @param task Task marked as done in Duke.
     */
    public void markAsDone(Task task) {
        print("Nice! I've marked this task as done:");
        print(task.toString());
    }

    /**
     * Prints a response from marking a Task as not done in Duke to the UI.
     *
     * @param task Task marked as not done in Duke.
     */
    public void markNotDone(Task task) {
        print("OK, I've marked this task as not done yet:");
        print(task.toString());
    }

    /**
     * Prints an information about the current number of tasks in Duke.
     *
     * @param count Number of tasks in Duke.
     */
    public void infoCount(int count) {
        print("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints a response from deleting a Task in Duke to the UI.
     *
     * @param task Task deleted in Duke.
     */
    public void deleteTask(Task task) {
        print("Noted. I've removed this task:");
        print(task.toString());
    }

    /**
     * Prints a goodbye message and closes the UI.
     */
    public void close() {
        print("Bye. Hope to see you again soon!");
        sc.close();
    }
}

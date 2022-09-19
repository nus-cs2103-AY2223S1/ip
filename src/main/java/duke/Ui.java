package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the UI component of Duke.
 */
public class Ui {
    protected Scanner sc = new Scanner(System.in);
    protected String output = "";

    /**
     * Prints a String to the UI.
     *
     * @param string String to print.
     */
    public void print(String string) {
        output += string + '\n';
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
        assert task != null;
        print("Got it. I've added this task:");
        print(task.toString());
    }

    /**
     * Prints all tasks in the TaskList to the UI.
     *
     * @param tasks List of tasks to print.
     */
    public void printTasks(TaskList tasks) {
        assert tasks != null;
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints a response from marking a Task as done in Duke to the UI.
     *
     * @param task Task marked as done in Duke.
     */
    public void markAsDone(Task task) {
        assert task != null;
        print("Nice! I've marked this task as done:");
        print(task.toString());
    }

    /**
     * Prints a response from marking a Task as not done in Duke to the UI.
     *
     * @param task Task marked as not done in Duke.
     */
    public void markNotDone(Task task) {
        assert task != null;
        print("OK, I've marked this task as not done yet:");
        print(task.toString());
    }

    /**
     * Prints an information about the current number of tasks in Duke.
     *
     * @param count Number of tasks in Duke.
     */
    public void infoCount(int count) {
        assert count >= 0;
        print("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints a response from deleting a Task in Duke to the UI.
     *
     * @param task Task deleted in Duke.
     */
    public void deleteTask(Task task) {
        assert task != null;
        print("Noted. I've removed this task:");
        print(task.toString());
    }

    /**
     * Print all matching tasks to the UI.
     *
     * @param tasks List of matching tasks.
     */
    public void findTasks(TaskList tasks) {
        assert tasks != null;
        print("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the nearest date without task to the UI.
     *
     * @param date Nearest date without task.
     */
    public void findFreeTimes(LocalDate date) {
        print("The nearest date without task is " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ".");
    }

    /**
     * Collects the output accumulated by the UI, returns it, and resets the UI output.
     *
     * @return Output accumulated in the UI.
     */
    public String collect() {
        String temp = output;
        output = "";
        return temp;
    }

    /**
     * Prints a goodbye message and closes the UI.
     */
    public void close() {
        print("Bye. Hope to see you again soon!");
        sc.close();
    }
}

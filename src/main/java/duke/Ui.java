package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * Represents class regarding user and system interaction
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Ui {
    private Scanner myScanner = new Scanner(System.in);

    /**
     * Prints welcome message to user
     */
    public void showWelcome() {
        String logo = "____________________________________________________________\n"
                + "Hello from\n"
                + " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "How may I assist you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
    }

    /**
     * Prints LoadingError if file cannot load
     */
    public void showLoadingError() {

        System.out.println("Unable to load file");
    }

    /**
     * Prints the prompt for user input
     */
    public String readCommand() {
        System.out.println("Please enter your command:");
        String command = myScanner.nextLine();
        return command;
    }

    /**
     * Prints the line
     */
    public void showLine() {

        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the error description in Duke exception
     * @param errorMsg the relevant error message
     *
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints farewell message to user
     */
    public void showBye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    /**
     * Prints prompt about success in marking task
     * @param task the specified task to be marked
     *
     */
    public void showMarked(Task task) {
        String taskCompletion = "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n";
        System.out.println(taskCompletion);
    }

    /**
     * Prints prompt about success in un-marking task
     * @param task the specified task to be unmarked
     *
     */
    public void showUnMarked(Task task) {
        String taskUnCompletion = "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n";
        System.out.println(taskUnCompletion);
    }

    /**
     * Prints the task list for user
     * @param tasks the list of tasks
     *
     */
    public void showList(ArrayList<Task> tasks) {
        String newList = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task item: tasks) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        System.out.println(newList);
    }

    /**
     * Prints prompt about success in deleting task
     * @param task the specified task to be deleted
     *
     */
    public void showDelete(Task task, int total) {
        String message = "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(message);
    }

    /**
     * Prints prompt about success in adding task
     * @param task the specified task to be added
     *
     */
    public void showAdd(Task task, int total) {
        String printLine = "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(printLine);
    }

    /**
     * Prints prompt about success in finding filtered tasks
     * @param filteredTaskList the filtered list of tasks
     *
     */
    public void showFind(ArrayList<Task> filteredTaskList) {
        String newList = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task item: filteredTaskList) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        System.out.println(newList);
    }
}

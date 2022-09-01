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

    public String showWelcome() {
        String logo =  "Hello from\n" +
                "How may I assist you?\n";
        return logo;

    }

    /**
     * Prints LoadingError if file cannot load
     */
    public String showLoadingError() {
        String loadingErrorMsg = "Unable to load file";
        return loadingErrorMsg;
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
     * Prints the error description in Duke exception
     * @param errorMsg the relevant error message
     *
     */
    public String showError(String errorMsg) {

        return errorMsg;
    }

    /**
     * Prints farewell message to user
     */
    public String showBye() {
        String bye = "Bye. Hope to see you again soon!\n";
        return bye;
    }

    /**
     * Prints prompt about success in marking task
     * @param task the specified task to be marked
     *
     */
    public String showMarked(Task task) {
        String taskCompletion = "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n";
        return taskCompletion;
    }

    /**
     * Prints prompt about success in un-marking task
     * @param task the specified task to be unmarked
     *
     */
    public String showUnMarked(Task task) {
        String taskUnCompletion = "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n";
        return taskUnCompletion;
    }

    /**
     * Prints the task list for user
     * @param tasks the list of tasks
     *
     */
    public String showList(ArrayList<Task> tasks) {
        String newList = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task item: tasks) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        return newList;
    }

    /**
     * Prints prompt about success in deleting task
     * @param task the specified task to be deleted
     *
     */
    public String showDelete(Task task, int total) {
        String message = "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        return message;
    }

    /**
     * Prints prompt about success in adding task
     * @param task the specified task to be added
     *
     */
    public String showAdd(Task task, int total) {
        String printLine = "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        return printLine;

    }


    /**
     * Prints prompt about success in finding filtered tasks
     * @param filteredTaskList the filtered list of tasks
     *
     */

    public String showFind(ArrayList<Task> filteredTaskList) {
        String newList = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task item: filteredTaskList) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        return newList;
    }
}

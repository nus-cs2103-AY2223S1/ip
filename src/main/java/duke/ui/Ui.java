package duke.ui;

import duke.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * a User Interface class that handles all the interactions with the user
 * has a Scanner object to take in inputs from the user
 * able to print out outputs for user to engage in interaction with Duke
 */
public class Ui {

    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * prints out welcome text
     */
    public String greetingsPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    /**
     * prints that error occur in reading of file
     */
    public String fileErrorPrint() {
        return ("Throwing error in file");
    }

    public void fileNotFoundPrint() {
        System.out.println("File not found error");
    }

    public void generalErrorPrint() {
        System.out.println("An error occurred.");
    }

    public void dukeEmptyPrint() {
        System.out.println("Nth to read form duke.txt");
    }

    /**
     * @param str command input
     * @return string with empty error description
     */
    private String emptyPrint(String str) {
        return(String.format("☹ OOPS!!! The description of a %s cannot be empty.", str));
    }

    /**
     * prints out empty numbers in mark command from users
     */
    public String emptyMarkPrint() {
        return (emptyPrint("mark"));
    }

    /**
     * prints out empty numbers in unmark command from users
     */
    public String emptyUnmarkPrint() {
        return (emptyPrint("unmark"));
    }

    /**
     * prints out empty numbers in Delete command from users
     */
    public String emptyDeletePrint() {
        return (emptyPrint("delete"));
    }

    /**
     * prints out empty numbers in ToDo command from users
     */
    public String emptyToDoPrint() {
        return (emptyPrint("ToDo"));
    }

    /**
     * prints out empty numbers in Event command from users
     */
    public String emptyEventPrint() {
        return (emptyPrint("event"));
    }

    /**
     * prints out empty numbers in Deadline command from users
     */
    public String emptyDeadlinePrint() {
        return (emptyPrint("deadline"));
    }

    public String emptyUpdatePrint() {
        return (emptyPrint("update"));
    }

    /**
     * @param no number of tasks left
     * @return formatted strings of no of task left
     */
    private String countTasks(int no) {
        return(String.format("Now you have %d tasks in the list.", no));
    }

    /**
     * @param task to be deleted
     * @param count no of tasks left
     * prints task removed and total no of tasks left
     */
    public String deletePrint(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task);
        sb.append('\n');
        sb.append(countTasks(count));
        return sb.toString();
    }

    /**
     * @param task to be added
     * @param count no of tasks left
     * prints tasks removed and total no of tasks left
     */
    public String addTaskPrint(Task task, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task);
        sb.append('\n');
        sb.append(countTasks(count));
        return sb.toString();
    }

    /**
     * no search results found for this find word
     */
    public String findNothingPrint() {
        return("Sorry, I cannot find what you are looking for");
    }

    public String findPrint(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for(Task task : tasks) {
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * prints unable to comprehend the instructions of the users
     */
    public String addUnknownPrint() {
        return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * prints user attempted to print an empty list of tasks
     */

    public String emptyListPrint() {
        return ("☹ OOPS!!! I'm sorry, but cannot print empty list");
    }
    /**
     * prints at the end of programme, when bye is inserted as command
     */
    public String byePrint() {
        return ("Bye. Hope to see you again soon!");
    }

    public void newDirectoryPrint() {
        System.out.println("hi, made new directory");
    }

    /**
     * @param task to be mark as done
     * prints the task as marked
     */
    public String markTaskPrint(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * @param task to be unmarked as done
     * prints the task as unmarked
     */
    public String unmarkTaskPrint(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(task);
        sb.append("\n");
        return sb.toString();
    }

    public String updatePrint(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've updated this task as:\n");
        sb.append(task);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * @return command input the user inserts
     */
    public String readInput() {
        return sc.nextLine();
    }
}

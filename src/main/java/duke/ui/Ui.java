package duke.ui;

import duke.tasks.Task;

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
    public void greetingsPrint() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * prints that error occur in reading of file
     */
    public void fileErrorPrint() {
        System.out.println("Throwing error in file");
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
    public void emptyMarkPrint() {
        System.out.println(emptyPrint("mark"));
    }

    /**
     * prints out empty numbers in unmark command from users
     */
    public void emptyUnmarkPrint() {
        System.out.println(emptyPrint("unmark"));
    }

    /**
     * prints out empty numbers in Delete command from users
     */
    public void emptyDeletePrint() {
        System.out.println(emptyPrint("delete"));
    }

    /**
     * prints out empty numbers in ToDo command from users
     */
    public void emptyToDoPrint() {
        System.out.println(emptyPrint("ToDo"));
    }

    /**
     * prints out empty numbers in Event command from users
     */
    public void emptyEventPrint() {
        System.out.println(emptyPrint("event"));
    }

    /**
     * prints out empty numbers in Deadline command from users
     */
    public void emptyDeadlinePrint() {
        System.out.println(emptyPrint("deadline"));
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
    public void deletePrint(Task task, int count) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(countTasks(count));
    }

    /**
     * @param task to be added
     * @param count no of tasks left
     * prints tasks removed and total no of tasks left
     */
    public void addTaskPrint(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(countTasks(count));
    }

    /**
     * prints unable to comprehend the instructions of the users
     */
    public void addUnknownPrint() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * prints user attempted to print an empty list of tasks
     */
    public void emptyList() {
        System.out.println("☹ OOPS!!! I'm sorry, but cannot print empty list");
    }

    /**
     * prints at the end of programme, when bye is inserted as command
     */
    public void byePrint() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void newDirectoryPrint() {
        System.out.println("hi, made new directory");
    }

    /**
     * @param task to be mark as done
     * prints the task as marked
     */
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * @param task to be unmarked as done
     * prints the task as unmarked
     */
    public void unmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * @return command input the user inserts
     */
    public String readInput() {
        return sc.nextLine();
    }
}

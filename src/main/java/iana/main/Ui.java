package iana.main;

import java.util.Scanner;

import iana.tasks.Task;
import iana.tasks.TaskList;

/**
 * User interface that will interact with user.
 */
public class Ui {
    private static String LINEBLOCK = "\t---------------------------------------------\n";
    private Scanner sc;

    /**
     * Constructor for Ui class to initialise system scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a line border.
     */
    public void showLine() {
        System.out.println(LINEBLOCK);
    }

    /**
     * Prints desired message surrounded by borders.
     * @param msg the message to be printed.
     */
    public void echo(String msg) {
        System.out.println(LINEBLOCK);
        System.out.println("\t" + msg + "\n");
        System.out.println(LINEBLOCK);
    }

    /**
     * Prints out task list of all current tasks.
     * @param tasks task list to be printed out.
     */
    public void list(TaskList tasks) {
        String listMessage = "\t> Here are the tasks in your list:\n" + tasks.toString();
        showLine();
        System.out.println(listMessage);
        showLine();
    }

    /**
     * Prints goodbye message.
     */
    public void sayBye() {
        echo("> Goodbye! :P");
    }

    /**
     * Prints welcome message.
     */
    public void sayHi() {
        echo("> Hello there~ I'm IANA.\n\tWhat can I do for you today? : )");
    }

    /**
     * Prints message to request new user input.
     */
    public void askNewCommand() {
        echo("\t> Try another action ><");
    }

    /**
     * Prints task information that is added to task list.
     * @param task task to be printed.
     */
    public void sayTaskAdded(Task task) {
        echo(String.format("> Nice! I have added the task to the list:\n\t   %s", task.toString()));
    }

    /**
     * Prints task that is deleted from task list.
     * @param task task that is deleted.
     * @param listSize number of tasks left in the task list.
     */
    public void sayTaskDeleted(Task task, int listSize) {
        echo(String.format("> Noted. I've removed this task:\n\t   %s\n\tNow you have %d tasks in the list.", 
        task.toString(), listSize));
    }

    /**
     * Reads user input from command line.
     * @return the user's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}

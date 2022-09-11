package iana.ui;

import iana.tasks.Task;
import iana.tasks.TaskList;
import java.util.Scanner;

/**
 * User interface that will interact with user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui class to initialise system scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns desired message surrounded by borders.
     * 
     * @param msg the message to be printed.
     */
    public String say(String msg) {
        return msg;
    }

    /**
     * Returns task list of all current tasks.
     * 
     * @param tasks task list to be printed out.
     */
    public String list(TaskList tasks) {
        String listMessage = "> Here are the tasks in your list:\n" + tasks.toString();
        return listMessage;
    }

    /**
     * Returns goodbye message.
     */
    public String sayBye() {
        return say("> Goodbye! :P");
    }

    /**
     * Returns welcome message.
     */
    public String sayHi() {
        return say("> Hello there~ I'm IANA.\n\tWhat can I do for you today? : )");
    }

    /**
     * Returns message to request new user input.
     */
    public String askNewCommand() {
        return say("> Sorry, I don't understand. Try another action!");
    }

    /**
     * Returns task information that is added to task list.
     * 
     * @param task task to be returned.
     */
    public String sayTaskAdded(Task task) {
        return say(String.format("> Nice! I have added the task to the list:\n\t   %s", task.toString()));
    }

    /**
     * Returns task that is deleted from task list.
     * 
     * @param task task that is deleted.
     * @param listSize number of tasks left in the task list.
     */
    public String sayTaskDeleted(Task task, int listSize) {
        return say(String.format("> Noted. I've removed this task:\n\t   %s\n\tNow you have %d tasks in the list.", 
        task.toString(), listSize));
    }

    /**
     * Reads user input from command line.
     * 
     * @return the user's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}

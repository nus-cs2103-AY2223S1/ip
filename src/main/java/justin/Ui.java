package justin;

import justin.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that is responsible for
 * sending outputs to users.
 * @author Justin Cheng.
 */
public class Ui {
    private final static String SEPARATOR = System.lineSeparator();

    private final static String WELCOME_MESSAGE = "Hi, I'm Justin. Type \"help\" for more info";
    private final static String GOODBYE_MESSAGE = "Bye! Hope to see you again soon.";
    private final static String UNMARK_MESSAGE = "Sure, I've marked the following task(s) as undone: ";
    private final static String MARK_MESSAGE =  "Nice! I have marked the following task(s) as done: ";
    private final static String LIST_MESSAGE = "Here are the tasks in your list: ";
    private final static String DELETE_MESSAGE = "OK, I have removed the following task(s) from the list: ";
    private final static String ADD_MESSAGE = "Got it, I have added the following into the list: ";
    private final static String FIND_MESSAGE = "Here are the matching tasks in your list: ";
    private final static String OVERLAP_MESSAGE = "You have some tasks overlapping! ";
    private final static String HELP_MESSAGE = "Try typing down any of these commands: \n"
            + "todo                       " + "\tadd a todo task\n"
            + "deadline                 " + "\tadd a deadline task\n"
            + "event                     " + "\tadd an event task\n"
            + "find                          " + "\tfind a specific task\n"
            + "delete <index>     " + "\tdelete a task\n"
            + "mark <index>       " + "\tmark a task as done\n"
            + "unmark <index> " + "\tmark a task as undone\n"
            + "list                             " + "\tlist out all the tasks in the list\n"
            + "bye                             " + "\tquit the app\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Default Constructor for the Ui class.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for the Ui class.
     * @param in An InputStream for inputs.
     * @param out A PrintStream to output messages.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the welcome message of the bot.
     * @return The welcome message in String.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns the goodbye message of the bot.
     * @return The goodbye message in String.
     */
    public String getGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns a line separator.
     * @return A line separator in String.
     */
    public String getSeparator() {
        return SEPARATOR;
    }

    /**
     * Returns the message when a Task is being unmarked.
     * @return The String message when a Task is unmarked.
     */
    public String getUnmarkMessage() {
        return UNMARK_MESSAGE + SEPARATOR;
    }

    /**
     * Returns the message when a Task is being marked.
     * @return The String message when a Task is marked.
     */
    public String getMarkMessage() {
        return MARK_MESSAGE + SEPARATOR;
    }

    /**
     * Returns the message when every Task in the TaskList
     * is listed.
     * @param list The TaskList that will be enumerated.
     * @return The String message when every Task is listed.
     */
    public String getListMessage(TaskList list) {
        int count = 1;
        String res = LIST_MESSAGE + SEPARATOR;
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
           res += SEPARATOR + count + ". " + task.toString();
           count++;
        }
        return res;
    }

    /**
     * Returns the message when a Task is being deleted.
     * @return The String message when a Task is deleted.
     */
    public String getDeleteMessage() {
        return DELETE_MESSAGE + SEPARATOR;
    }

    /**
     * Returns the message when a Task is being added
     * into the TaskList.
     * @return The String message when a Task is added.
     */
    public String getAddMessage() {
        return ADD_MESSAGE + SEPARATOR;
    }

    /**
     * Returns the message when a user wants to find
     * the number of Tasks in the TaskList.
     * @param list The TaskList to be enumerated.
     * @return The String message when a user wants to find
     * out the number of Tasks in the TaskList.
     */
    public String getCountMessage(TaskList list) {
        int size = list.getTasks().size();
        if (size == 1) {
            return "You now have " + size + " task in your list.";
        } else {
            return "You now have " + size + " tasks in your list.";
        }
    }

    /**
     * Returns the message that includes overlapping tasks.
     * @param overlappedTasks An ArrayList of Tasks.
     * @return The String output of overlapped Tasks.
     */
    public String getOverlapMessage(ArrayList<Task> overlappedTasks) {
        String msg = OVERLAP_MESSAGE + SEPARATOR;
        int count = 1;
        for (Task task: overlappedTasks) {
            msg += SEPARATOR + count + ". " + task.toString();
            count++;
        }
        return msg;
    }

    /**
     * Prints out any text that is passed in the method.
     * @param text the String text to be printed out.
     */
    public void showText(String text) {
        out.println(text);
    }

    /**
     * Returns the message when users want to find matching
     * tasks with the description.
     * @param list The TaskList of lists.
     * @param description The keywords for finding tasks.
     * @return The String message when users want to find
     * tasks.
     */
    public String getFindMessage(TaskList list, String description) {
        String res = FIND_MESSAGE + SEPARATOR;
        int count = 1;
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
            if (task.getDescription().contains(description)) {
                res += SEPARATOR + count + ". " + task;
                count++;
            }
        }
        return res;
    }

    public String getHelpMessage() {
        return HELP_MESSAGE;
    }

}

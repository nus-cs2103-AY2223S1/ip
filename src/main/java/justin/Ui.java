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
    private final static String DIVIDER = "--------------------------------------------------------";

    private final static String SEPARATOR = System.lineSeparator();

    private final static String WELCOME_MESSAGE = "Hi, I'm Justin. What do you want me to do for you?";
    private final static String GOODBYE_MESSAGE = "Bye! Hope to see you again soon.";
    private final static String UNMARK_MESSAGE = "OK, I've marked your task as undone: ";
    private final static String MARK_MESSAGE =  "Nice! I have marked this task as done: ";
    private final static String LIST_MESSAGE = "Here are the tasks in your list: ";
    private final static String DELETE_MESSAGE = "OK, I have removed the following task from the list: ";
    private final static String ADD_MESSAGE = "Got it, I have added the following into the list: ";
    private final static String FIND_MESSAGE = "Here are the matching tasks in your list: ";
    private final static String TODO_HELP = "Type in todo <task name> to add ToDo task";
    private final static String DEADLINE_HELP = "Type in deadline <task name> /by YYYY-MM-DD HH:MM to add Deadline task";
    private final static String EVENT_HELP = "Type in event <task name> /by YYYY-MM-DD HH:MM to add Event task";

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
     * Prints out the welcome message.
     */
    public String welcome() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints out the goodbye message.
     */
    public String goodbye() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Prints out the divider.
     */
    public String showLine() {
        return SEPARATOR;
    }

    /**
     * Returns the String that is input by the user.
     * @return String representation of input message.
     */
    public String readCommand() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    /**
     * Prints out the outputs when a Task is being unmarked.
     * @param task The Task that is to be unmarked.
     */
    public String unmarkMessage(Task task) {
        return UNMARK_MESSAGE + SEPARATOR + task.toString();
    }

    /**
     * Prints out the outputs when a Task is being marked.
     * @param task The Task that is to be marked.
     */
    public String markMessage(Task task) {
        return MARK_MESSAGE + SEPARATOR + task.toString();
    }

    /**
     * Prints out every Task in the TaskList.
     * @param list The TaskList that will be enumerated.
     */
    public String listMessage(TaskList list) {
        String res = LIST_MESSAGE;
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
           res += SEPARATOR + task.toString();
        }
        return res;
    }

    /**
     * Prints out the outputs when a Task is being deleted.
     * @param task The Task to be deleted.
     */
    public String deleteMessage(Task task) {
        return DELETE_MESSAGE + SEPARATOR + task.toString();
    }

    /**
     * Prints out the outputs when a Task is being added
     * into the TaskList.
     * @param task The Task to be added.
     */
    public String addMessage(Task task) {
        return ADD_MESSAGE + SEPARATOR + task.toString();
    }

    /**
     * Prints out the number of Tasks in the TaskList.
     * @param list The TaskList to be enumerated.
     */
    public String countMessage(TaskList list) {
        int size = list.getTasks().size();
        return "You now have " + size + " tasks in your list";
    }

    /**
     * Prints out any text that is passed in the method.
     * @param text the String text to be printed out.
     */
    public void showText(String text) {
        out.println(text);
    }

    public String findMessage(TaskList list, String description) {
        String res = FIND_MESSAGE;
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
            if (task.getDescription().contains(description)) {
                res += SEPARATOR+ task;
            }
        }
        return res;
    }

}

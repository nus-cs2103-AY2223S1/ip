package duke;

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
    public void welcome() {
        out.println(DIVIDER + SEPARATOR + WELCOME_MESSAGE + SEPARATOR + DIVIDER);
    }

    /**
     * Prints out the goodbye message.
     */
    public void goodbye() {
        out.println(DIVIDER + SEPARATOR + GOODBYE_MESSAGE + SEPARATOR + DIVIDER);
    }

    /**
     * Prints out the divider.
     */
    public void showLine() {
        out.println(DIVIDER);
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
    public void unmarkMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + UNMARK_MESSAGE + SEPARATOR + DIVIDER);
        out.println(task.toString() + SEPARATOR);
    }

    /**
     * Prints out the outputs when a Task is being marked.
     * @param task The Task that is to be marked.
     */
    public void markMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + MARK_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    /**
     * Prints out every Task in the TaskList.
     * @param list The TaskList that will be enumerated.
     */
    public void listMessage(TaskList list) {
        out.println(DIVIDER + SEPARATOR + LIST_MESSAGE + SEPARATOR);
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
            out.println(task.toString());
        }
        out.println(DIVIDER);
    }

    /**
     * Prints out the outputs when a Task is being deleted.
     * @param task The Task to be deleted.
     */
    public void deleteMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + DELETE_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    /**
     * Prints out the outputs when a Task is being added
     * into the TaskList.
     * @param task The Task to be added.
     */
    public void addMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + ADD_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    /**
     * Prints out the number of Tasks in the TaskList.
     * @param list The TaskList to be enumerated.
     */
    public void countMessage(TaskList list) {
        int size = list.getTasks().size();
        out.println("You now have " + size + " tasks in your list");
    }

    /**
     * Prints out any text that is passed in the method.
     * @param text the String text to be printed out.
     */
    public void showText(String text) {
        out.println(text);
    }

}

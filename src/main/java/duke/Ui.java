package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

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

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void welcome() {
        out.println(DIVIDER + SEPARATOR + WELCOME_MESSAGE + SEPARATOR + DIVIDER);
    }

    public void goodbye() {
        out.println(DIVIDER + SEPARATOR + GOODBYE_MESSAGE + SEPARATOR + DIVIDER);
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public String readCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        return inputLine;
    }

    public void unmarkMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + UNMARK_MESSAGE + SEPARATOR + DIVIDER);
        out.println(task.toString() + SEPARATOR);
    }

    public void markMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + MARK_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    public void listMessage(TaskList list) {
        out.println(DIVIDER + SEPARATOR + LIST_MESSAGE + SEPARATOR);
        ArrayList<Task> tasks = list.getTasks();
        for (Task task: tasks) {
            out.println(task.toString());
        }
        out.println(DIVIDER);
    }

    public void deleteMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + DELETE_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    public void addMessage(Task task) {
        out.println(DIVIDER + SEPARATOR + ADD_MESSAGE + SEPARATOR + task.toString() + SEPARATOR + DIVIDER);
    }

    public void countMessage(TaskList list) {
        int size = list.getTasks().size();
        out.println("You now have " + size + " tasks in your list");
    }

    public void showText(String text) {
        out.println(text);
    }

}

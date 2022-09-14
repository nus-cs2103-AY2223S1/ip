package Duke.UI;

import Duke.Task.Task;
import Duke.TaskList.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the interface that the user interacts with.
 */
public class Ui {
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOME_MESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String CREATE_MESSAGE = "Got it. I've added this task:";
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String NOT_FOUND_MESSAGE = "There are no tasks in your list with this keyword";

    // Error Messages
    private static final String ERROR_PREFIX = "DukeError";

    // scanner
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message when the user first starts the application.
     */
    public void printWelcome() {
        prettyPrint(WELCOME_MESSAGE);
    }

    /**
     *  Prints the goodbye message when the user closes the program.
     */
    public void printGoodbye() {
        sc.close();
        prettyPrint(GOODBYE_MESSAGE);
    }

    /**
     * Prints error messages when an exception is thrown and passed back to the parser.
     *
     * @param e The exception passed to the parser.
     */
    public void printException(Exception e) {
        prettyPrint(String.format("%s: %s", ERROR_PREFIX, e.toString()));
    }

    /**
     * Prints a task after it has been successfully created.
     *
     * @param task A successfully created task.
     */
    public void printTaskCreated(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", CREATE_MESSAGE, TAB,  taskStr));
    }

    /**
     * Prints a task after it has been successfully marked as complete.
     *
     * @param task A successfully marked task.
     */
    public void printTaskMarked(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", MARK_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints a task after it has been successfully marked as incomplete.
     *
     * @param task A successfully unmarked task.
     */
    public void printTaskUnmarked(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", UNMARK_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints a task after it has been successfully deleted.
     *
     * @param task A successfully deleted task.
     */
    public void printTaskDeleted(Task task) {
        String taskStr = task.toString();
        prettyPrint(String.format("%s\n%s %s", DELETE_MESSAGE, TAB,  taskStr));
    }

    /**
     * Prints out the entire list of tasks stored in the taskList.
     *
     * @param taskList The object that stores all the user's tasks.
     */
    public void printAll(TaskList taskList) {
        List<Task> taskArrayList = taskList.getTaskList();
        List<String> printables = new ArrayList<>();
        printables.add(LIST_MESSAGE);
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            int index = i + 1;
            printables.add(String.format("%d.%s", index, task.toString()));
        }
        prettyPrint(printables);
    }

    /**
     * Prints a message to indicate successful finding, followed by an indexed list of found tasks.
     * If no tasks are found, prints a message to indicate failure to find any tasks.
     *
     * @param foundTasks an array of found tasks from TaskList that match keyword
     */
    public void printFoundTasks(Task[] foundTasks) {
        if (foundTasks.length <= 0) {
            prettyPrint(NOT_FOUND_MESSAGE);
            return;
        }
        List<String> printables = new ArrayList<>();
        printables.add(FOUND_MESSAGE);
        for (int i = 0; i < foundTasks.length; i++) {
            Task task = foundTasks[i];
            int index = i + 1;
            printables.add(String.format("%d.%s", index, task.toString()));
        }
        prettyPrint(printables);
    }

    private void prettyPrint() {
        prettyPrint("");
    }

    private void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    private void prettyPrint(List<String> printables) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printables.size(); i++) {
            String s = printables.get(i);
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append(String.format("\n%s %s", TAB, s));
            }
        }
        String printable = sb.toString();
        prettyPrint(printable);
    }

    /**
     * Retrieves the next word that the user keys into the application.
     *
     * @return The next user inputted word.
     */
    public String getNext() {
        return sc.next();
    }

    /**
     * Retrieves the next line of text that the user keys into the application.
     *
     * @return The next user inputted line.
     */
    public String getNextLine() {
        return sc.nextLine();
    }
}

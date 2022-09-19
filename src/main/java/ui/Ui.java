package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import task.Task;
import tasklist.TaskList;

/**
 * Represents the interface that the user interacts with.
 */
public class Ui {
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "______________________________________________________");
    private static final String WELCOME_MESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String CREATE_MESSAGE = "Got it. I've added this task:";
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String EMPTY_TASKLIST_MESSAGE = "You do not have any tasks in your list.";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String NOT_FOUND_MESSAGE = "There are no tasks in your list with this keyword.";

    // Error Messages
    private static final String ERROR_PREFIX = "DukeError";

    // scanner
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message when the user first starts the application.
     *
     * @return String Welcome dialog to be read by user.
     */
    public String printWelcome() {
        return prettyPrint(WELCOME_MESSAGE);
    }

    /**
     *  Prints the goodbye message when the user closes the program.
     *
     * @return String Goodbye dialog to be read by user.
     */
    public String printGoodbye() {
        sc.close();
        return prettyPrint(GOODBYE_MESSAGE);
    }

    /**
     * Prints error messages when an exception is thrown and passed back to the parser.
     *
     * @param e The exception passed to the parser.
     * @return String Error message dialogue to be read by user.
     */
    public String printException(Exception e) {
        assert e != null: "Exception to be printed cannot be null";
        return prettyPrint(String.format("%s: %s", ERROR_PREFIX, e.toString()));
    }

    /**
     * Prints a task after it has been successfully created.
     *
     * @param task A successfully created task.
     * @return String Task created dialogue to be read by user.
     */
    public String printTaskCreated(Task task) {
        assert task != null: "Created task to be printed cannot be null";
        String taskStr = task.toString();
        return prettyPrint(String.format("%s\n%s %s", CREATE_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints a task after it has been successfully marked as complete.
     *
     * @param task A successfully marked task.
     * @return String Task marked dialogue to be read by user.
     */
    public String printTaskMarked(Task task) {
        assert task != null: "Marked task to be printed cannot be null";
        String taskStr = task.toString();
        return prettyPrint(String.format("%s\n%s %s", MARK_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints a task after it has been successfully marked as incomplete.
     *
     * @param task A successfully unmarked task.
     * @return String Task unmarked dialogue to be read by user.
     */
    public String printTaskUnmarked(Task task) {
        assert task != null: "Unmarked task to be printed cannot be null";
        String taskStr = task.toString();
        return prettyPrint(String.format("%s\n%s %s", UNMARK_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints a task after it has been successfully deleted.
     *
     * @param task A successfully deleted task.
     * @return String Task deleted dialogue to be read by user.
     */
    public String printTaskDeleted(Task task) {
        assert task != null: "Deleted task to be printed cannot be null";
        String taskStr = task.toString();
        return prettyPrint(String.format("%s\n%s %s", DELETE_MESSAGE, TAB, taskStr));
    }

    /**
     * Prints out the entire list of tasks stored in the taskList.
     *
     * @param taskList The object that stores all the user's tasks.
     * @return String List of all Tasks dialogue to be read by user.
     */
    public String printAll(TaskList taskList) {
        assert taskList != null: "TaskList to be printed cannot be null";
        List<Task> taskArrayList = taskList.getTaskList();
        if (taskArrayList.size() <= 0) {
            return prettyPrint(EMPTY_TASKLIST_MESSAGE);
        }
        List<String> printables = IntStream.range(0, taskArrayList.size())
                .mapToObj(index -> String.format("%d. %s", index + 1, taskArrayList.get(index).toString()))
                        .collect(Collectors.toList());

        return prettyPrint(LIST_MESSAGE, printables);
    }

    /**
     * Prints a message to indicate successful finding, followed by an indexed list of found tasks.
     * If no tasks are found, prints a message to indicate failure to find any tasks.
     *
     * @param foundTasks an array of found tasks from TaskList that match keyword
     * @return String List of all tasks that match keyword dialogue to be read by user.
     */
    public String printFoundTasks(Task[] foundTasks) {
        assert foundTasks != null: "List of found tasks to be printed cannot be null";
        if (foundTasks.length <= 0) {
            return prettyPrint(NOT_FOUND_MESSAGE);
        }
        List<String> printables = IntStream.range(0, foundTasks.length)
                .mapToObj(index -> String.format("%d. %s", index + 1, foundTasks[index].toString()))
                        .collect(Collectors.toList());

        return prettyPrint(FOUND_MESSAGE, printables);
    }

    private String prettyPrint(String printable) {
        assert printable != null: "String to be printed cannot be null";
        return String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
    }

    private String prettyPrint(String prefix, List<String> printables) {
        String printable = printables.stream()
                .map(x->String.format("%s %s", TAB, x))
                        .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
        printable = prefix + "\n" + printable;
        return prettyPrint(printable);
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

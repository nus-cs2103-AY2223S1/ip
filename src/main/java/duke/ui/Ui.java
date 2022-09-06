package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.tasks.Task;

/**
 * This class encapsulates the user interface
 */
public class Ui {

    private final Scanner scanner;
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you? ^_^";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon :D";
    private static final String INVALID_MESSAGE = "I don't know what this means :(";
    private static final String NO_TASKS_ADDED = "There are no tasks added!";
    private final static String NO_MATCHING_TASKS = "No matching tasks!";
    private final static String MARK_TASK_FORMAT = "Nice! I have marked this task as done:\n\n%s";
    private final static String UNMARK_TASK_FORMAT = "Okay! I have marked this task as not done:\n\n%s";
    private final static String NUMBER_OF_TASKS_LEFT_FORMAT = "\n\nNow you have %d %s in the list!";
    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs a new Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user whenever the application starts
     * @return The welcome message
     */
    public String printWelcome() {
        return WELCOME_MESSAGE;
    }

    /**
     * Exits when the user chooses to stop the application
     * @return The exit message
     */
    public String printExit() {
        this.scanner.close();
        return EXIT_MESSAGE;
    }

    /**
     * Prints the task that is just added
     * @param addedTask The task that is just added
     * @param numOfTasks The number of tasks in the list
     * @return A message to notify the user that a new task is added
     */
    public String printAddTask(Task addedTask, int numOfTasks) {
        String header = "Got it! I have added this task:\n\n" + addedTask;
        String tasks = String.format(NUMBER_OF_TASKS_LEFT_FORMAT, numOfTasks, numOfTasks > 1 ? "tasks" : "task");
        return header + tasks;
    }

    /**
     * Prints the task that is just deleted
     * @param deletedTask The deleted task
     * @param numOfTasks The number of tasks remaining
     * @return The message to notify user that a task has been deleted
     */
    public String printDeleteTask(Task deletedTask, int numOfTasks) {
        String msg = String.format("Noted, I have removed this task:\n\n%s", deletedTask);
        String tasks = String.format(NUMBER_OF_TASKS_LEFT_FORMAT, numOfTasks, numOfTasks > 1 ? "tasks" : "task");
        return msg + tasks;
    }

    /**
     * Prints the task that is marked as completed
     * @param task The task that is marked as completed
     * @return The message to notify user that the task has been marked as completed
     */
    public String printMarkTask(Task task) {
        return String.format(MARK_TASK_FORMAT, task);
    }

    /**
     * Prints the task that is marked as not completed
     * @param task The task that is marked as not completed
     * @return The message to notify the user that the task been marked as not completed
     */
    public String printUnmarkTask(Task task) {
        return String.format(UNMARK_TASK_FORMAT, task);
    }

    /**
     * Prints the exception's message
     * @param exception The exception thrown and caught
     * @return The exception's message
     */
    public String printException(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Gets the input provided by the user
     * @return The input provided
     */
    public String input() {
        return this.scanner.nextLine();
    }

    /**
     * Notifies users about invalid commands
     * @return ignore
     * @throws DukeException If the command is invalid
     */
    public String printInvalid() throws DukeException {
        throw new DukeException(INVALID_MESSAGE);
    }

    /**
     * Prints the list of tasks
     * @param list The list of tasks
     * @return The list of tasks after formatting it
     */
    public String printList(ArrayList<Task> list) {
        if (list.size() == 0) {
            return NO_TASKS_ADDED;
        }

        int len = list.size();
        String header = String.format("Here %s the %s in your list :D\n",
                len > 1 ? "are" : "is",
                len > 1 ? "tasks" : "task");
        StringBuilder stringBuilder = new StringBuilder(header);

        for (int i = 0; i < len; i++) {
            String task = String.format("\n%d. %s", i + 1, list.get(i));
            stringBuilder.append(task);
        }

        return stringBuilder.toString();
    }

    /**
     * Prints the tasks after executing the tasks command
     * @param list The list of tasks to be printed
     * @param date Date of the tasks
     * @return A string consisting of the tasks
     */
    public String printList(ArrayList<Task> list, LocalDate date) {
        if (list.size() == 0) {
            return String.format("No tasks on %s!", date.format(DATE_FORMAT));
        }

        int len = list.size();
        String header = String.format("Your %s for %s include:\n", len > 1 ? "tasks" : "task", date.format(DATE_FORMAT));
        StringBuilder stringBuilder = new StringBuilder(header);

        for (int i = 0; i < len; i++) {
            Task t = list.get(i);
            if (t.getTaskType().equals("D") || t.getTaskType().equals("E")) {
                String formatted = String.format("\n%d. %s", i + 1, t);
                stringBuilder.append(formatted);
            }

        }

        return stringBuilder.toString();
    }

    /**
     * Prints the tasks returned after executing the find command
     * @param list List of tasks to print
     * @return A string of the tasks
     */
    public String printFind(ArrayList<Task> list) {
        if (list.size() == 0) {
            return NO_MATCHING_TASKS;
        }

        int len = list.size();
        String header = String.format("Here %s the matching %s in your list:\n",
                len > 1 ? "are" : "is",
                len > 1 ? "tasks" : "task");
        StringBuilder stringBuilder = new StringBuilder(header);

        for (int i = 0; i < len; i++) {
            String item = String.format("\n%d. %s", i + 1, list.get(i));
            stringBuilder.append(item);
        }

        return stringBuilder.toString();
    }

    /**
     * Prints the input
     * @param input A string to be printed
     * @return The string to be printed
     */
    public String print(String input) {
        return input;
    }
}

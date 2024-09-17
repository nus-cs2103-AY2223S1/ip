package duke;

import duke.task.Task;

/**
 * Class interacting with user input and printing output.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    /**
     * Returns a string containing the Duke logo and greeting message.
     *
     * @return String that contains the opening message.
     */
    public String printGreeting() {
        return LOGO + GREETING;
    }

    /**
     * Closes the scanner for reading input.
     *
     * @return String that contains the closing message.
     */
    public String close() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns a message with the specified list of tasks.
     *
     * @param list A list of tasks in string.
     * @return String that contains all the tasks in the list.
     */
    public String printList(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Returns a message with the size of specified list.
     *
     * @param size The size of the list.
     * @return String that contains the size of the list.
     */
    public String printSizeOfList(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Returns a message of marking of specified task as complete.
     *
     * @param task The task that is marked as complete.
     * @return String that contains the task that has been marked as complete.
     */
    public String printMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + printTask(task);
    }

    /**
     * Returns a message of marking of specified task as incomplete.
     *
     * @param task The task that is marked as incomplete.
     * @return String that contains the task that has been marked as not complete.
     */
    public String printUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + printTask(task);
    }

    /**
     * Returns a message of deleting specified task.
     *
     * @param task The task that is deleted.
     * @return String that contains the task that has been deleted.
     */
    public String printDeleteTask(Task task) {
        return "Noted. I've removed this task:\n" + printTask(task);
    }

    /**
     * Returns a message of adding specified task.
     *
     * @param task The task that is added.
     * @return String that contains the task that has been added to the list.
     */
    public String printAddTask(Task task) {
        return "Got it. I've added this task:\n" + printTask(task);
    }

    /**
     * Prints a specified error message from given exception.
     *
     * @param exception The exception.
     * @return String that contains the exception that has been thrown by Duke.
     */
    public String printErrorMessage(Exception exception) {
        return exception.getMessage() + '\n';
    }

    private String printTask(Task task) {
        assert task != null;
        return task.toString() + '\n';
    }
}

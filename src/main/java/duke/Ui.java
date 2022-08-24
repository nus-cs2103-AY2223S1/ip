package duke;

/**
 * Ui Class handling user interaction
 *
 * @author Elbert Benedict
 */
public class Ui {
    /**
     * Prints Welcome Message.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello from Botto\nWhat can I do for you?");
    }

    /**
     * Prints Goodbye Message.
     */
    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the newly added task by the user.
     *
     * @param task the newly added task.
     * @param taskNumber the number of tasks in the Tasklist.
     */
    public static void printTaskAdded(Task task, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    /**
     * Prints the tasklist added by the user.
     *
     * @param taskList the list of tasks to be printed.
     */
    public static void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.printSelf();
    }

    /**
     * Print the newly deleted task.
     *
     * @param task the newly deleted task.
     * @param taskLeft the number of tasks left in the list.
     */
    public static void printDeletedTask(Task task, int taskLeft) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskLeft + " tasks in the list.");
    }

    /**
     * Print the task that has been newly marked.
     *
     * @param task the newly marked task.
     */
    public static void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Print the task that hast just been unmarked.
     *
     * @param task the task that is just unmarked.
     */
    public static void printUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints error message of a DukeException.
     *
     * @param error The DukeException which message is to be printed.
     */
    public static void printDukeError(DukeException error) {
        System.out.println(error.getMessage());
    }

    /**
     * Prints the list of filtered tasks.
     *
     * @param filteredTasks the filtered tasks.
     */
    public static void printFilteredTasks(TaskList filteredTasks) {
        System.out.println("Here are the matching tasks in your list:");
        filteredTasks.printSelf();
    }
}

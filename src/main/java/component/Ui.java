package component;

/**
 * Public user interface class.
 */
public class Ui {
    protected static final String OPENING_STATEMENT = "Mew Mew.\n"
            + "What can I do for you?";
    protected static final String EXIT_STATEMENT = "Bye. Hope to see you again soon! :)";
    protected static final String LOADING_ERROR_STATEMENT = "Oops, there is a loading error!";
    protected static final String TASK_LIST_STATEMENT = "Here are the tasks in your list:\n";
    protected static final String MARK_AS_DONE_STATEMENT = "Nice! I've marked this task as done:\n";
    protected static final String UNMARK_AS_DONE_STATEMENT = "Okay, I've marked this task as not done yet:\n";
    protected static final String ADD_TASK_STATEMENT = "Got it. I've added this task:\n";
    protected static final String DELETE_TASK_STATEMENT = "Noted, I've removed this task:\n";
    protected static final String ASK_WHICH_TASK_STATEMENT = "Wait, which task are you referring to?";
    protected static final String FIND_MATCHING_TASK_STATEMENT = "Here are the matching tasks in your list:\n";
    protected static final String UNKNOWN_STATEMENT = "Oops, sorry! I don't know what that means :(";

    /**
     * Shows the opening statement.
     * @return Opening statement
     */
    public static String showOpeningStatement() {
        return OPENING_STATEMENT;
    }

    /**
     * Shows the exit statement.
     * @return Exit statement
     */
    public static String showExitStatement() {
        return EXIT_STATEMENT;
    }

    /**
     * Shows loading error.
     * @return Loading error statement
     */
    public static String showLoadingError() {
        return LOADING_ERROR_STATEMENT;
    }

    /**
     * Shows the task list.
     * @param tasks Task list
     * @return Task list
     */
    public static String showTaskList(TaskList tasks) {
        return TASK_LIST_STATEMENT + tasks;
    }

    /**
     * Shows that marking of a task is completed.
     * @param task Marked task
     * @return Mark as done statement and the marked task
     */
    public static String showMarkDone(Task task) {
        return MARK_AS_DONE_STATEMENT + task;
    }

    /**
     * Shows that unmarking of a task is completed.
     * @param task Unmarked task
     * @return Unmark as done statement and the unmarked task
     */
    public static String showUnmarkAsDone(Task task) {
        return UNMARK_AS_DONE_STATEMENT + task;
    }

    /**
     * Shows that addition of a Task is complete.
     * @param task Added task
     * @param numberOfTasks Total number of tasks
     * @return Add task statement, the added task, and the total number of tasks
     */
    public static String showAddTaskDone(Task task, int numberOfTasks) {
        return ADD_TASK_STATEMENT + task + "\n"
                + "Now you have " + numberOfTasks + " tasks in your list.\n";
    }

    /**
     * Shows that deletion of a Task is completed.
     * @param deletedTask Deleted task
     * @param numberOfTasks Number of remaining tasks
     * @return Delete task statement, the deleted task, and the remaining number of tasks
     */
    public static String showDeleteTaskDone(Task deletedTask, int numberOfTasks) {
        return DELETE_TASK_STATEMENT + deletedTask + "\n"
                + "Now you have " + numberOfTasks + " tasks in your list.\n";
    }

    /**
     * Asks the user for a specific Task.
     * @return Statement asking for a specific task
     */
    public static String askWhichTask() {
        return ASK_WHICH_TASK_STATEMENT;
    }

    /**
     * Shows matching tasks.
     * @param tasks TaskList Matching tasks
     * @return A list of matching tasks
     */
    public static String showFoundTasks(TaskList tasks) {
        return FIND_MATCHING_TASK_STATEMENT + tasks;
    }

    /**
     * Shows unknown statement when the command is unknown.
     * @return Unknown statement
     */
    public static String showUnknownCommand() {
        return UNKNOWN_STATEMENT;
    }

    /**
     * Prints an exception.
     * @param e Exception
     * @return Printed form of the exception
     */
    public static String printException(Exception e) {
        return "Exception: " + e;
    }
}
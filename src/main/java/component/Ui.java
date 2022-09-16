package component;

/**
 * Public user interface class.
 */
public class Ui {
    protected static final String OPENING_STATEMENT = "Meow~\n"
            + "Mew here. How can I help you?\n"
            + "Send `help` for a list of commands.";
    protected static final String HELP_STATEMENT = "Here are the list of commands that I understand!\n"
            + "`todo DESC`\n    => add To-Do\n"
            + "`deadline DESC /by DATETIME`\n    => add Deadline\n"
            + "`event DESC /at DATETIME`\n    => add Event\n"
            + "`list`\n    => list all tasks\n"
            + "`mark TASK_NUMBER`\n    => marks a task as done\n"
            + "`unmark TASK_NUMBER`\n    => unmarks a task as not done\n"
            + "`delete TASK_NUMBER`\n    => delete task\n"
            + "`edit TASK_NUMBER NEW_DESC`\n    => edit task description\n"
            + "`find KEYWORD`\n    => find task\n"
            + "`help`\n    => list all commands\n"
            + "`bye`\n    => exits Mew";
    protected static final String EXIT_STATEMENT = "Bye! Good luck and have fun ><";
    protected static final String LOADING_ERROR_STATEMENT = "Oops, there is a loading error!";
    protected static final String TASK_LIST_STATEMENT = "Here's what I have:\n";
    protected static final String MARK_AS_DONE_STATEMENT = "Noice! I've marked this task as done:\n";
    protected static final String UNMARK_AS_DONE_STATEMENT = "Alright, I've marked this task as not done:\n";
    protected static final String EDIT_TASK_DONE_STATEMENT = "Gotcha, I've edited this task:\n";
    protected static final String ADD_TASK_STATEMENT = "Okay. I've added this task:\n";
    protected static final String DELETE_TASK_STATEMENT = "Noted, I've removed this task:\n";
    protected static final String ASK_WHICH_TASK_STATEMENT = "Wait, which task are you referring to?";
    protected static final String FIND_MATCHING_TASK_STATEMENT = "Here's what you searched for:\n";
    protected static final String EMPTY_EVENT_DESCRIPTION_STATEMENT = "Oops, description and time of an Event cannot "
            + "be empty.";
    protected static final String EMPTY_DEADLINE_DESCRIPTION_STATEMENT = "Oops, description and time of a Deadline "
            + "cannot be empty.";
    protected static final String EMPTY_TODO_DESCRIPTION_STATEMENT = "Oops, description of a To-do "
            + "cannot be empty.";
    protected static final String INVALID_DATETIME_FORMAT_STATEMENT = "Please enter date and time in YYYYMMDD HHMM "
            + "format";
    protected static final String UNKNOWN_STATEMENT = "Oops, I don't know what that means T_T";

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
     * Lists all the available commands.
     * @return Help statement
     */
    public static String showHelpStatement() {
        return HELP_STATEMENT;
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
     * Shows that edit process of a Task is completed.
     * @param editedTask Edited task
     * @return Edit task done statement, the edited task
     */
    public static String showEditTaskDone(Task editedTask) {
        return EDIT_TASK_DONE_STATEMENT + editedTask + "\n";
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
     * Shows warning when a description and/or date time of an Event is not given.
     * @return Warning statement
     */
    public static String showEmptyEventDescriptionWarning() {
        return EMPTY_EVENT_DESCRIPTION_STATEMENT;
    }

    /**
     * Shows warning when a description and/or date time of a Deadline is not given.
     * @return Warning statement
     */
    public static String showEmptyDeadlineDescriptionWarning() {
        return EMPTY_DEADLINE_DESCRIPTION_STATEMENT;
    }

    /**
     * Shows warning when a description of a To-do is not given.
     * @return Warning statement
     */
    public static String showEmptyToDoDescriptionWarning() {
        return EMPTY_TODO_DESCRIPTION_STATEMENT;
    }

    /**
     * Shows warning when invalid date time format is given.
     * @return Warning statement
     */
    public static String showInvalidDatetimeInput() {
        return INVALID_DATETIME_FORMAT_STATEMENT;
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
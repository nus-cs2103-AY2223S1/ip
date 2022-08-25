package duke.common;
/**
 * Stores the general messages to show the users.
 * The messages are utilise in BotUI class.
 */
public class Messages {

    public static final String MESSAGE_BYE = "Goodbye, Hope to see you soon!\n"
            + "----------------------------------------------\n";
    public static final String MESSAGE_HI = "Hello! I'm Duke\n" + "I'm ready to serve you!";
    public static final String MESSAGE_BOT_DIVIDER = "~~~~~-----DUKE-----~~~~~\n";
    public static final String MESSAGE_USER_DIVIDER = "~~~~~-----YOU-----~~~~~~\n";
    public static final String MESSAGE_NOTHING_IN_LIST = "Nothing is added to the list!";
    public static final String MESSAGE_MARK_SUCCESS = "Nice! this task is marked as done. Good Job!\n%s";
    public static final String MESSAGE_UNMARK_SUCCESS = "This task is marked as not done. Keep it up!\n%s";
    public static final String MESSAGE_ADD_SUCCESS = "New task is registered as you wish,"
            + " you can come back to check if you wish!:\n %s\n"
            + "Now you have %d tasks on your list.";
    public static final String MESSAGE_DELETE_SUCCESS = "Ching Ching Poof~~ This task is removed:\n %s\n"
            + "Now you have %d tasks on your list.";
    public static final String MESSAGE_SHOW_FORMAT = "Based on my understanding,"
            + " your instruction didn't follow the format\n"
            + "todo : todo [task description]\n"
            + "deadline: deadline [task description] /by [YYYY-MM-DD HHmm]\n"
            + "event: event [task description] /at [YYYY-MM-DD HHmm]\n"
            + "individual command: bye | list |\n"
            + "mark/unmark/delete: [command] [number of task in list you wish to modify])\n"
            + "find: find [search keyword]";
    public static final String MESSAGE_TASK_NOT_EXIST = "Opps! we only have %d tasks in the list :(";
    public static final String MESSAGE_REQUIRE_INTEGER = "Sorry, last character after mark/unmark/delete"
            + " command should be integer!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Date and time format should be [YYYY-MM-DD HHmm]!\n"
            + "(eg. 2022-08-21 1300)";
    public static final String MESSAGE_FOUND_TASK = "Here you go! your matching tasks in your list";
}

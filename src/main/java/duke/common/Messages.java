package duke.common;
/**
 * Stores the general messages to show the users.
 * The messages are utilise in BotUI class.
 */
public class Messages {

    public static final String BYE = "Goodbye, Hope to see you soon!";
    public static final String HI = "Hello! I'm Duke\n" + "I'm ready to serve you!";
    public static final String BOT_RESPONSE = "~~~~~-----DUKE-----~~~~~\n";
    public static final String USER_SAY = "~~~~~-----YOU-----~~~~~~\n";
    public static final String NOTHING_IN_LIST = "Nothing is added to the list!";
    public static final String MARK_SUCCESS = "Nice! this task is marked as done. Good Job!\n%s";
    public static final String UNMARK_SUCCESS = "This task is marked as not done. Keep it up!\n%s";
    public static final String ADD_SUCCESS = "New task is registered as you wish,"
            + " you can come back to check if you wish!:\n %s\n"
            + "Now you have %d tasks on your list.";
    public static final String DELETE_SUCCESS = "Ching Ching Poof~~ This task is removed:\n %s\n"
            + "Now you have %d tasks on your list.";
    public static final String SHOW_FORMAT = "Based on my understanding,"
            + " your command didn't follow the format\n"
            + "todo              : todo [task description]\n"
            + "deadline          : deadline [task description] /by [YYYY-MM-DD HHmm]\n"
            + "event             : event [task description] /at [YYYY-MM-DD HHmm]\n"
            + "single command    : | bye | list |\n"
            + "mark/unmark/delete: [command] [number of task in list you wish to modify]\n"
            + "find              : find [search keyword]";
    public static final String TASK_NOT_EXIST = "Opps! we only have %d tasks in the list :(";
    public static final String REQUIRE_INTEGER = "Sorry, last character after mark/unmark/delete"
            + " command should be integer!";
    public static final String INVALID_DATE_FORMAT = "Date and time format should be [YYYY-MM-DD HHmm]!\n"
            + "(eg. 2022-08-21 1300)";
    public static final String FOUND_TASK = "Here you go! your matching tasks in your list";
    public static final String NOT_FOUND = "Nothing in the list match your search keyword!";
    public static final String DATE_TIME_FORMAT = "MMM dd yyyy HH:mm";
    public static final String SHOW_LIST_DESCRIPTION = "Weeeee, your current list is as follow:\n";
    public static final String TODO_ID = "[T]";
    public static final String EVENT_ID = "[E]";
    public static final String DEADLINE_ID = "[D]";
    public static final String UNKNOWN_RESPONSE = "Haizz, unknown response detected, try again la.";
    public static final String SAME_TASK_DETECTED = "YO! these two tasks have same detail, task date and "
            + "time will be reset or nothing will change if the task is (todo) type. "
            + "Are you Sure? (Y/N)\n" + "New Task: %s\nExisting Task: %s";
    public static final String SAME_DATE_DETECTED = "Hey, these two tasks timing are quite close,"
            + " are you sure to proceed? (Y/N)\n" + "New Task: %s\nExisting Task: %s";
    public static final String COMMAND_CANCELED = "Alright! the previous command is canceled.";
}

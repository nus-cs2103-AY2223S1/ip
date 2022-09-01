package duke.messages;

/**
 * Contains the chatbot's responses and messages after handling commands.
 */
public final class ResponseMessages {

    public static String STARTUP = "Hello! I'm dukebot\n" +
                     "What can I do for you?\n";
    public static String ENDING = "Bye. Hope to see you again soon!\n";
    public static String LIST_TASKS = "Here are the tasks in your list:\n";
    public static String TASK_MARKED = "Nice! I've marked this task as done:\n";
    public static String TASK_UNMARKED = "OK, I've marked this task as not done yet:\n";
    public static String TASK_ADDED = "Got it. I've added this task:\n";
    public static String TASK_COUNT = "Now you have %d task(s) in the list.\n";
    public static String TASK_REMOVED = "Noted. I've removed this task:\n";
    public static String FIND_TASKS = "Here are tasks matching '%s' in your list:\n";
    public static String LOAD_ERROR = "Error loading data from storage\n";

}

package duke.messages;

/**
 * Contains the chatbot's responses and messages after handling commands.
 */
public final class ResponseMessages {

    private static final String lineSep = System.lineSeparator();

    public static String STARTUP = "Hello! I'm dukebot, what can I do for you?";
    public static String ENDING = "Bye. Hope to see you again soon!";
    public static String LIST_TASKS = "Here are the tasks in your list:";
    public static String TASK_MARKED = "Nice! I've marked this task as done:";
    public static String TASK_UNMARKED = "OK, I've marked this task as not done yet:";
    public static String TASK_ADDED = "Got it. I've added this task:";
    public static String TASK_COUNT = "Now you have %d task(s) in the list.";
    public static String TASKS_REMOVED = "Noted. I've removed these task(s):";
    public static String FIND_TASKS = "Here are tasks matching '%s' in your list:";
    public static String LOAD_ERROR = "Error loading data from storage";

}

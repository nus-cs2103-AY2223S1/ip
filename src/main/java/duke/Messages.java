package duke;

/**
 * Contains the chatbot's responses and messages after handling commands.
 */
public final class Messages {

    static String STARTUP = "Hello! I'm dukebot\n" +
                     "What can I do for you?";
    static String ENDING = "Bye. Hope to see you again soon!";
    static String LIST_TASKS = "Here are the tasks in your list:";
    static String TASK_MARKED = "Nice! I've marked this task as done:";
    static String TASK_UNMARKED = "OK, I've marked this task as not done yet:";
    static String TASK_ADDED = "Got it. I've added this task:";
    static String TASK_COUNT = "Now you have %d task(s) in the list.";
    static String TASK_REMOVED = "Noted. I've removed this task:";
    static String FIND_TASKS = "Here are tasks matching '%s' in your list:";
    static String LOAD_ERROR = "Error loading data from storage";

}

package duke;

/**
 * Contains all the constant strings to be used in classes
 */
public class Constants {
    public static final String CHATBOX_NAME = "Ado";
    public static final String PARTITION = "<><><><><><><><><><><><><><><><><><><><><><><><><><><><>";
    public static final String NEW_WELCOME_MESSAGE = "What's up? I'm " + CHATBOX_NAME + ", How may i help you :-)"
            + "\nSince this is your first time, some sample tasks has been added for you! "
            + "\nType \"help\" for the list of available commands";
    public static final String WELCOME_MESSAGE = "What's up? I'm " + CHATBOX_NAME + ", How may i help you :-)"
            + "\nType \"help\" for the list of available commands";
    public static final String BYE_MESSAGE = "I've saved your tasks! Hope to see you again ~\n";
    public static final String LIST_EMPTY_MESSAGE = "List is empty ~\n";
    public static final String LIST_MESSAGE = "Here are the tasks in your list: \n";
    public static final String MATCHING_TASK_MESSAGE = "Here are the matching tasks containing";
    public static final String NOMATCHING_TASK_MESSAGE = "No matching tasks with";
    public static final String LOAD_TASK_ERROR_MESSAGE = "Error in loading task :( New task list created!\n";
    public static final String INVALID_COMMAND_MESSAGE = "? I don't know what that means\n";
    public static final String MISSING_DESCRIPTION_MESSAGE = "The description of cannot be empty.\n";
    public static final String MISSING_DATE_MESSAGE = "The date of deadline cannot be empty.\n";
    public static final String INVALID_DATE_MESSAGE = "Put date after /by in terms of yyyy-MM-dd\n";
    public static final String HELP_MESSAGE = "List of commands:\ntodo: adds a todo task"
            + "\ndeadline: adds a deadline task\nevent:adds a event task\nmark: marks a task at specified index"
            + "\nunmark: unmarks a task at specified index\n\nlist: lists all saved task"
            + "\nfind: finds a task with keyword\nbye:exits the chatbot\nhelp: lists all commands";
}

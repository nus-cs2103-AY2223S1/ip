package duke;

/**
 * Contains all the constant strings to be used in classes
 */
public class Constants {
    public static final String CHATBOX_NAME = "Ado";

    public static final String HELP_DETAILED_MESSAGE = "List of commands:"
            + "\ntodo: adds a todo task [Description]\ne.g. todo feed cat"
            + "\ndeadline: adds a deadline task [Description, date]\ne.g. deadline Do homework /by 12 Mar 2022"
            + "\nevent:adds a event task [Description, date, time]"
            + "\ne.g. event LOTR meet and greet /at 28 Nov 2022  20:30"
            + "\nmark: marks a task at specified index\ne.g. mark 2"
            + "\nunmark: unmarks a task at specified index\ne.g. unmark 1"
            + "\nfind: finds a task with keyword\ne.g. find homework"
            + "\n\nlist: lists all saved task"
            + "\nbye:exits Ado chatbot";
    public static final String HELP_MESSAGE = "Need help? You can click on the \"?\" icon beside the send button to"
            + " see the list of available commands!";

    public static final String NEW_WELCOME_MESSAGE = "Yo! I'm " + CHATBOX_NAME + ", your personal task tracker :-)"
            + "\nSince this is your first time, some sample tasks has been added for you to play around! \n"
            + "\nHappy exploring ~\n\n" + HELP_MESSAGE;
    public static final String WELCOME_MESSAGE = "What's up? " + CHATBOX_NAME
            + " reporting for duty, How may i help you :-)";

    public static final String BYE_MESSAGE = "I've saved your tasks! Come back soon ~\n";
    public static final String LIST_EMPTY_MESSAGE = "List is empty, go make yourself busy!\n";
    public static final String LIST_MESSAGE = "Here are the tasks in your list, go do it! \n";
    public static final String MATCHING_TASK_MESSAGE = "Here are the matching tasks containing";
    public static final String NOMATCHING_TASK_MESSAGE = "No matching tasks with";
    public static final String LOAD_TASK_ERROR_MESSAGE = "Error in loading task :( New task list created!\n";
    public static final String INVALID_SINGLE_COMMAND_MESSAGE = "Single commands should not have any text behind it\n";
    public static final String INVALID_COMMAND_MESSAGE = "? What does that mean???\n";
    public static final String MISSING_DESCRIPTION_MESSAGE = "The description of command is EMPTY, fill it up.\n";
    public static final String MISSING_DEADLINEDATE_MESSAGE = "How could you forget to put a deadline date? D:<\n";
    public static final String MISSING_EVENTDATE_MESSAGE = "How could you forget to put a event date? D:<\n";
    public static final String INVALID_DEADLINEDATE_MESSAGE = "Put date after /by in terms of d MMM yyyy\n";
    public static final String INVALID_EVENTDATE_MESSAGE = "Put date after /at in terms of d MMM yyyy HH:mm\n";
}

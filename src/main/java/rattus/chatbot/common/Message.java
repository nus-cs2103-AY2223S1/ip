package rattus.chatbot.common;

/**
 * Contains the messages that are used by the application.
 *
 * @author jq1836
 */
public class Message {
    public static final String MESSAGE_WELCOME = "Hello there, my name's Rattus! How may I help you?";
    public static final String MESSAGE_BYE = "Bye! Till we next meet!";
    public static final String MESSAGE_LIST = "Here are your tasks:";
    public static final String MESSAGE_EMPTY_LIST = "You do not have any tasks that match your query!";
    public static final String MESSAGE_ADDED_TASK = "I've added the following task:";
    public static final String MESSAGE_MARKED = "I've gone ahead and marked this task for you:";
    public static final String MESSAGE_UNMARKED = "Alright, I've unmarked this for you:";
    public static final String MESSAGE_DELETED = "Hey! I've deleted this task as requested:";
    public static final String MESSAGE_FILTERED_TASKS = "These are the tasks that correspond to your query:";

    public static final String MESSAGE_NEW_FILE = "I've created this new file just like you asked:";
    public static final String MESSAGE_CHANGE_FILE = "I've changed the working file to:";
    public static final String MESSAGE_DELETE_FILE = "I've deleted this file:";

    public static final String MESSAGE_FILE_ALREADY_EXISTS = "Hey! The file you want to create already exists!";
    public static final String MESSAGE_CANNOT_DELETE_FILE = "Hey! You can't delete the file you are viewing! Change "
            + "files before trying again!";
    public static final String MESSAGE_INVALID_FILE_NAME = "It seems that your file does not exist! Try again!";
    public static final String MESSAGE_INVALID_ARGUMENT = "It seems that you have keyed your arguments in wrongly! "
            + "Try again!";
    public static final String MESSAGE_OUT_OF_LIST_RANGE = "It seems that the entry number you have keyed in "
            + "does not exist. Try again!";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "It seems you have keyed in too many arguments. "
            + "Try again!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "It seems you have keyed the date in the wrong format. "
            + "Try something like this '2022-01-01'!";
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "It seems you have keyed the date and time in the "
            + "wrong format. Try something like '2022-01-01 1500'!";
    public static final String MESSAGE_UNEXPECTED = "Sorry, I couldn't get what you said. Try again!";
    public static final String MESSAGE_INIT_FAILED = "An unexpected error has occurred, try running again or call "
            + "technical support!";
}

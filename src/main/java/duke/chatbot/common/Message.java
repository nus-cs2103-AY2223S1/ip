package duke.chatbot.common;

/**
 * Contains the messages that are used by the application.
 * @author jq1836
 */
public class Message {
    public static final String MESSAGE_WELCOME = "\t"
            + " ,-----.,--.               ,--.    ,--.                  " + "\n" + "\t"
            + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  " + "\n" + "\t"
            + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  " + "\n" + "\t"
            + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) " + "\n" + "\t"
            + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  " + "\n\n" + "\t"
            + "Hello there, my name's Chattus! How may I help you?";
    public static final String MESSAGE_BYE = "Bye! Till we next meet!";
    public static final String MESSAGE_LIST = "Here are your tasks:";
    public static final String MESSAGE_EMPTY_LIST = "You do not have any tasks that match your query!";
    public static final String MESSAGE_ADDED_TASK = "I've added the following task:";
    public static final String MESSAGE_UNEXPECTED = "It seems you have keyed in something incorrectly. Try again!";
    public static final String MESSAGE_MARKED = "I've gone ahead and marked this task for you:";
    public static final String MESSAGE_UNMARKED = "Alright, I've unmarked this for you:";
    public static final String MESSAGE_DELETED = "Hey! I've deleted this task as requested:";
    public static final String MESSAGE_CHECK_DATE = "These are the tasks that correspond to the date:";
    public static final String MESSAGE_FIND_KEYWORD = "These are the tasks that correspond to your keyword:";
    public static final String MESSAGE_INIT_FAILED = "An unexpected error has occured,"
            + " try running again or call technical support!";
}

package utils;

public class Constants {

    public static final String MSG_GREETINGS = "Hello! I'm Duke.\n\tWhat can I do for you?";
    public static final String MSG_EXIT = "Bye. Hope to see you again soon!";

    public static final String MSG_TASK_MARK = "Nice! I've marked this task as done:";
    public static final String MSG_TASK_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String MSG_TASK_ADDED = "Got it. I've added this task:";
    public static final String MSG_TASK_DELETED = "Noted. I've removed this task:";
    public static final String MSG_TASK_NUMBER = "Now you have %d tasks in the list.";
    public static final String MSG_TASK_LIST = "Here are the tasks in your list:";

    public static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_EMPTY_DESCRIPTION = "☹ OOPS!!! The description of a %s cannot be empty.";
    public static final String ERROR_TASK_NOT_SPECIFIED = "☹ OOPS!!! Please specify the task number (check list).";
    public static final String ERROR_NOT_NUMERIC = "☹ OOPS!!! Please enter a valid task number after %s";
    public static final String ERROR_INVALID_NUMBER = "☹ OOPS!!! The task number you have specified is invalid.";
    public static final String ERROR_INVALID_ARGUMENTS = "☹ OOPS!!! Incorrect number of arguments. " +
            "Please check command list.";
    public static final String ERROR_DEADLINE_EMPTY_DATE_TIME = "☹ OOPS!!! " +
            "Please set a deadline by adding '/by {date/time}' at the end of your task." +
            "\n\tReplace {date/time} with the date/time you wish to finish the task by.";
    public static final String ERROR_EVENT_EMPTY_TIME = "☹ OOPS!!! " +
            "Please set a time frame by adding '/at {time}' at the end of your task." +
            "\n\tReplace {time} with the time frame of the event.";
}

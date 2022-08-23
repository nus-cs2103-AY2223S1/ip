package duke.common;

public class Messages {

    public static final String MESSAGE_DEADLINE = "When does this need to get completed by?";
    public static final String MESSAGE_EVENT = "When is this event happening?";

    public static final String MESSAGE_TASK_NUMBER = "Task number: ";
    public static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_TASK_DESCRIPTION = "Give a brief overview of your task:";
    public static final String MESSAGE_TASK_ADDED =  "I have added the task! Type 'list' to show all tasks.";
    public static final String MESSAGE_TASK_REMOVE = "Which task would you like to remove?";
    public static final String MESSAGE_TASK_REMOVED = "I have removed the task: %s. Type 'list' to show all tasks.\n";
    public static final String MESSAGE_MARK_TASK = "Which task would you like to mark as complete: ";
    public static final String MESSAGE_TASK_MARKED = "You have marked task %s as complete! Congrats!\n" +
            "If you want to mark another duke.task as complete, please type 'mark' again.\n" +
            "Else, you can input a new task!\n";

    public static final String MESSAGE_INVALID_TASK_NUMBER = "Are you sure this number corresponds to a task?\n";
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command!";
    public static final String MESSAGE_EMPTY_TASK_DESCRIPTION = "Task description cannot be nothing! " +
            "Since you gave me nothing I'll return you nothing!";

    public static final String MESSAGE_OTHER_ACTIONS = "What else would you like to do?\n";

}

public class Ui {
    private static final String startUpMessage  = "Hello! I'm Duke\n"
                                                + "What can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String exitCmd = "bye";
    private static final String noTaskMessage   = "It appears you have no tasks right now,\n"
                                                + "would you like to add some?";
    private static final String addTaskMessage = "Got it. I've added this task:\n";
    private static final String taskMarkedMessage = "Nice! I've marked this task as done:\n";
    private static final String taskUnmarkedMessage = "OK, I've marked this task as not done yet:\n";
    private static final String alreadyMarkedMessage = "This task is already marked:\n";
    private static final String alreadyUnmarkedMessage = "This task is already unmarked:\n";
    private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
    private static final String noSuchTaskMessage   = "It appears there is no such task, \n"
                                                    + "Please try again";
    private static final String invalidInputMessage = "The input is invalid, please try again.";
    private static final String noDescriptionMessage = "The description of the task cannot be empty.";
    private static final String noTimeGivenMessage  = "Please provide the relevant time for this type of task,\n"
                                                    + "by typing \"/\" followed by the time.";
    private static final String invalidDateMessage  = "Invalid date provided.\n"
                                                    + "Please format the date in YYYY-MM-DD";
    private static final String noIndexGivenMessage = "Please provide the index of he relevant task after the\n"
                                                    + "command.";
    private static final String dataFileErrorMessage    = "There appears to be an issue retrieving your previous records";
}

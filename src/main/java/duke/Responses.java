package duke;

/**
 * A representation for the UI the user will see.
 */
public class Responses {
    /**
     * The message to greet the user with
     */
    public static final String INITIAL_MESSAGE = " Hello! I'm Duke. What can I do for you?";
    /**
     * The message to exit with
     */
    public static final String BYE_MESSAGE = ("      Bye. This doesn't have to be the end!\n");

    /**
     * Returns a string representing the number of tasks in the list.
     * @param taskList The task list
     * @return The String
     */
    public static String generateTasksNumberMessage(TaskList taskList) {
        return "Now you have " + taskList.sizeOfList() + " task" + (taskList.sizeOfList() == 1 ? "" : "s")
                + " in the list.";
    }

    /**
     * Generates an error message if the description is empty
     * @param taskType The type of the task
     * @return The String
     */
    public static String generateEmptyDescMessage(String taskType) {
        return "OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

}

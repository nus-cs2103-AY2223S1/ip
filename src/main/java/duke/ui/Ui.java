package duke.ui;

import duke.data.TaskList;
import duke.task.Task;

/**
 * Formats and returns UI messages.
 */
public class Ui {


    /**
     * Creates a new Ui object.
     */
    public Ui() {
    }


    /**
     * Returns the welcome message.
     *
     * @return Welcome message.
     */
    public static String getWelcomeMessage() {
        String firstLine = "Hello from Duke!\n";
        String secondLine = "What can I do for you?\n";

        return String.format("%s%s", firstLine, secondLine);
    }


    /**
     * Returns a String listing the Tasks in the specified TaskList object line by line.
     *
     * @param tasks TaskList object to list.
     * @param isSearchResult boolean value to indicate whether the TaskList object was from a search result.
     * @return String listing all Task objects line by line.
     */
    public String getListTasksMessage(TaskList tasks, boolean isSearchResult) {

        // Modify the header sentence based on whether the specified TaskList is from a search result
        String result = isSearchResult ? "Here are the matching tasks in your list:\n"
                                        : "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.getSize(); i++) {
            String line = String.format("%d. %s\n", i + 1, tasks.getTask(i));
            result = result.concat(line);
        }

        return result;
    }


    /**
     * Returns the mark Task message.
     *
     * @param t Task that was marked complete.
     * @return Mark Task message.
     */
    public String getMarkTaskMessage(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s\n", t);
    }


    /**
     * Returns the unmark Task message.
     *
     * @param t Task that was unmarked.
     * @return Unmark Task message.
     */
    public String getUnmarkTaskMessage(Task t) {
        return String.format("OK, I've marked this task as undone:\n%s\n", t);
    }


    /**
     * Returns the add Task message.
     *
     * @param t Task that was added.
     * @param numOfTasks Number of Task objects in the TaskList adding.
     * @return Add Task message.
     */
    public String getAddTaskMessage(Task t, int numOfTasks) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
            t, numOfTasks);
    }


    /**
     * Returns the delete Task message.
     *
     * @param t Task that was deleted.
     * @param numOfTasks Number of Task objects in the TaskList after deleting.
     * @return Delete Task message.
     */
    public String getDeleteTaskMessage(Task t, int numOfTasks) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
            t, numOfTasks);
    }


    /**
     * Returns the exit message.
     *
     * @return Exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }


    /**
     * Returns the data file success message.
     *
     * @return Data file success message.
     */
    public String getDataFileSuccessMessage() {
        return "Data file successfully read.\n";
    }


    // Error Messages

    /**
     * Returns the invalid command message.
     *
     * @return Invalid command message.
     */
    public String getInvalidCommandErrorMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }


    /**
     * Returns the add Task invalid syntax error message.
     *
     * @param e IllegalArgumentException that was thrown.
     * @return Add Task invalid syntax error message.
     */
    public String getAddTaskInvalidSyntaxErrorMessage(IllegalArgumentException e) {
        return String.format("OOPS!!! Invalid syntax for a %s task\n", e.getMessage());
    }


    /**
     * Returns the data file not found error message.
     *
     * @return Data file not found error message.
     */
    public String getDataFileNotFoundErrorMessage() {
        return "Cannot find data.ser file.\n";
    }


    /**
     * Returns the data file read error message.
     *
     * @return Data file read error message.
     */
    public String getDataFileReadErrorMessage() {
        return "Error when reading data file.\n";
    }


    /**
     * Returns the data file deserialize error message.
     *
     * @return Data file deserialize error message.
     */
    public String getDataFileDeserializeErrorMessage() {
        return "Error, data file can't be deserialized.\n";
    }


    /**
     * Returns the create new TaskList message.
     * Used if data file could not be read in successfully.
     *
     * @return Create new TaskList message.
     */
    public String getCreateNewTaskListMessage() {
        return "Creating new task list.\n";
    }

}

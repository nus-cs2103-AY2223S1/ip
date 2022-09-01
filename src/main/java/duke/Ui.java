package duke;

/**
 * This class handles the display for user interaction.
 */
public class Ui {

    protected final String ADDED = "oke i added this:";
    protected final String DELETED = "oke i deleted this:";
    protected final String MARKED = "oke this is done now:";
    protected final String UNMARKED = "oke this is undone now:";


    /**
     * Constructor for the Ui class.
     */
    public Ui () {

    }


    /**
     * Prints an exit message.
     *
     * @return String message.
     */
    public String showExit() {
        return "bye see u\n";
    }

    /**
     * Displays a task.
     *
     * @param message Message to be printed.
     * @param task Task to be displayed.
     * @return String displaying the task.
     */
    public String displayTask(String message, Task task) {
        return message + "\n" + task + "\n";
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param taskList Tasklist to be printed.
     * @return String containing all the tasks.
     */
    public String printTasks(TaskList taskList) {
        return "here! ur tasks:\n" + taskList + "\n";
    }

    /**
     * Displays the total amount of tasks
     * in the taskList.
     *
     * @param taskList Tasklist to be used.
     * @return String containing the total number of tasks.
     */
    public String showTotalTasks(TaskList taskList) {
        return "now u have " + taskList.getSize() + " task(s)!\n";
    }

    /**
     * Displays the matching tasks for the user.
     *
     * @param taskList Tasklist to be displayed.
     * @return String showing tasks with matching keyword.
     */
    public String showMatchingTasks(TaskList taskList) {
        if(taskList.getSize() > 0) {
            return "here are the matching tasks: \n" + taskList + "\n";
        } else {
           return "there are no tasks matching this keyword!\n";
        }
    }
    
    /**
     * Displays an error message.
     *
     * @param message Message to be displayed.
     * @return String with error message.
     */
    public String showError(String message) {
        return "error! " + message + "\n";
    }


}

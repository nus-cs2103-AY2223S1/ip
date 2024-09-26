package duke;

/**
 * Represents a Ui representation of Duke.
 * A <code>Ui</code> object corresponds to
 * user interface of Duke.
 */
public class Ui {
    private static final String GREETING = "HELLO MY FRIEND! HOW CAN I HELP YOU?";
    private static final String BYE = "GOOD BYE! SEE YOU IN WHILE!";

    /**
     * Returns string representation of greeting.
     *
     * @return String.
     */
    public static String showGreet() {
        return GREETING;
    }

    /**
     * Returns string representation of bye.
     *
     * @return String.
     */
    public String showBye() {
        return BYE;
    }

    /**
     * Returns task successfully added to taskList.
     *
     * @param task task to be added.
     * @param numOfTask number of task inside taskList.
     * @return String representation of task added.
     */
    public String addSuccess(Task task, String numOfTask) {
        assert !task.toString().isBlank() : "task content should not be blank";
        assert !numOfTask.isBlank() : "number of task should not be blank";

        return "Added: " + task.toString() + "\n" + numOfTask;
    }

    /**
     * Returns list of all tasks.
     *
     * @param taskList task to be added.
     * @return String representation of all task inside the taskList.
     */
    public String showList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns taskList of task found.
     *
     * @param findList taskList found.
     * @return String representation of taskList found.
     */
    public String showFind(TaskList findList) {
        return "HERE ARE THE TASKS THAT YOU REQUESTED:\n"
                + findList.toString();
    }

    /**
     * Returns task toggled successfully.
     *
     * @param task task to be toggled.
     * @return String representation of task toggled.
     */
    public String showToggleSuccess(Task task) {
        assert !task.toString().isBlank() : "task content should not be blank";
        if (!task.isDone()) {
            return "I HAVE CHANGED:\n" + task.toString();
        } else {
            return "GOOD JOB MY FRIEND!\n" + task.toString();
        }
    }

    /**
     * Returns task successfully deleted.
     *
     * @param task task to be deleted.
     * @param numOfTask number of task inside taskList.
     * @return String representation of task deleted.
     */
    public String showDeleteSuccess(Task task, String numOfTask) {
        assert !task.toString().isBlank() : "task content should not be blank";
        return "Deleted: " + task.toString() + "\n" + numOfTask;
    }
}

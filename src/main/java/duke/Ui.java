package duke;

public class Ui {

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcome() {
        return "Hello! I'm Duke.\n"
                + "What can I do for you?";
    }

    /**
     * Returns invalid task index error message.
     *
     * @return Invalid task index error message.
     */
    public String getInvalidTaskIndexError() {
        return "This task number is invalid!";
    }

    /**
     * Returns task added message.
     *
     * @param task Task that was added.
     * @param tasks TaskList that the Task was added to.
     * @return Task added message.
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        return "Okay, I've added this task:\n" + task.toString()
                + "\nYou now have " + tasks.size() + " tasks.";
    }
}

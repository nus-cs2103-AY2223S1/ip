package duke.command.response;

import duke.data.tasks.Task;

public class AddTaskResponse extends CommandResponse {

    private static final String ADD_TASK_RESPONSE_TEMPLATE = String.join(
        "\n",
        "Got it. I've added this task:",
        "\t%s",
        "Now you have %d tasks in the list"
    );

    /**
     * Constructor for AddTaskResponse class.
     *
     * @param addedTask a newly added task to the task list.
     * @param numTasks current number of tasks in the task list.
     */
    public AddTaskResponse(Task addedTask, int numTasks) {
        super(String.format(ADD_TASK_RESPONSE_TEMPLATE, addedTask, numTasks), true, false);
    }
}

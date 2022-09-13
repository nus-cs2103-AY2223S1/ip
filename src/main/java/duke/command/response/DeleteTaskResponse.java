package duke.command.response;

import duke.data.tasks.Task;

public class DeleteTaskResponse extends CommandResponse {

    private static final String DELETE_TASK_RESPONSE_TEMPLATE = String.join(
        "\n",
        "Noted. I've removed this task:",
        "\t%s",
        "Now you have %d tasks in the list"
    );

    /**
     * Constructor for DeleteTaskResponse class.
     *
     * @param deletedTask deleted task from the task list.
     * @param numTasks the current number of tasks in the task list.
     */
    public DeleteTaskResponse(Task deletedTask, int numTasks) {
        super(String.format(DELETE_TASK_RESPONSE_TEMPLATE, deletedTask, numTasks), true, false);
    }
}

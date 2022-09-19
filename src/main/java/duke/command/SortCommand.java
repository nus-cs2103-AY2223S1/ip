package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Represents a command to sort dated tasks according to their dates.
 */
public class SortCommand extends Command {

    /* Type of task to be shown - Event, Deadline or both */
    private String taskType;

    /**
     * Initialises the SortCommand with the task type of tasks to be sorted.
     * @param taskType Type of tasks to be displayed.
     */
    public SortCommand(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Initialises the SortCommand with all deadline and events to be sorted.
     */
    public SortCommand() {
        this.taskType = "dated";
    }

    /**
     * Executes this command to sort all deadlines and events, or just deadlines or events.
     * @param tasks Task list that contains tasks to be sorted.
     * @param storage Storage used in application.
     * @return The response of the execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        TaskList sortedTasks = tasks.sort(taskType);
        String message = sortedTasks.toString();
        return new Response(message, false);
    };
}

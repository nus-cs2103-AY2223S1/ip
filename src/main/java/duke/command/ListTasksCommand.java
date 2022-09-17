package duke.command;

import duke.Storage;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.TaskList;

/**
 * Represents a command that lists out all the tasks of the specified task list.
 */
public class ListTasksCommand extends Command {
    /**
     * Executes the ListTasksCommand and displays all the tasks in the specified task list in a list.
     *
     * @param tasks The task list whose tasks are to be listed.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return A list type Response containing the user's entire task list, and an overview message
     *         of the user's uncompleted tasks.
     */
    @Override
    public Response<TaskList> execute(TaskList tasks, Storage storage) {
        String tasksOverviewMessage = tasks.numberOfTasks();
        return new Response<>(ResponseType.LIST, tasksOverviewMessage, tasks);
    }
}

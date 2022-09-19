package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * A command that marks a specified task.
 */
public class MarkTaskCommand extends Command {

    private Storage storage;
    private TaskList tasks;
    private String index;
    private boolean hasExecutedSuccessfully;

    /**
     * Constructs a new command to mark the task corresponding to the specified
     * index.
     * @param storage Storage object for storing the list of tasks.
     * @param tasks TaskList object in use by the app.
     * @param index The index of the task to be marked.
     */
    public MarkTaskCommand(Storage storage, TaskList tasks, String index) {
        this.storage = storage;
        this.tasks = tasks;
        this.index = index;
        hasExecutedSuccessfully = false;
    }

    /**
     * Marks the Task.
     * @return Response message.
     */
    @Override
    public String execute() {
        try {
            Task markedTask = tasks.markTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Nice! I've marked this task as done:\n  %s",
                    markedTask);
            hasExecutedSuccessfully = true;
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        } catch (NumberFormatException e) {
            String response = String.format("Oops! I could not recognise this format: %s\n", index);
            return response;
        }
    }

    /**
     * Un-marks the marked task.
     * @return Response message.
     */
    @Override
    public String undo() {
        if (!hasExecutedSuccessfully) {
            return "Oops! Your previous mark task command did not complete successfully, "
                    + "so there is nothing to undo.";
        }

        try {
            Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Got it. I've unmarked the task you marked previously:\n  %s",
                    unmarkedTask);
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        }
    }

}

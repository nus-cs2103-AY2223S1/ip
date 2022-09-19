package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * A command that un-marks a specified task.
 */
public class UnmarkTaskCommand extends Command {

    private Storage storage;
    private TaskList tasks;
    private String index;
    private boolean hasExecutedSuccessfully;

    /**
     * Constructs a new command to unmark the task corresponding to the specified index.
     * @param storage Storage object for storing the list of tasks.
     * @param tasks TaskList object in use by the app.
     * @param index Index of the task to be unmarked.
     */
    public UnmarkTaskCommand(Storage storage, TaskList tasks, String index) {
        this.storage = storage;
        this.tasks = tasks;
        this.index = index;
        hasExecutedSuccessfully = false;
    }

    /**
     * Un-marks the Task.
     * @return Response message.
     */
    @Override
    public String execute() {
        try {
            Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "OK, I've marked this task as not done yet:\n  %s`",
                    unmarkedTask);
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
     * Marks the previously un-marked Task.
     * @return Response message.
     */
    @Override
    public String undo() {
        if (!hasExecutedSuccessfully) {
            return "Oops! Your previous unmark task command did not complete successfully, "
                    + "so there is nothing to undo.";
        }

        try {
            Task markedTask = tasks.markTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Got it. I've marked the task you unmarked previously:\n  %s",
                    markedTask);
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        }
    }

}

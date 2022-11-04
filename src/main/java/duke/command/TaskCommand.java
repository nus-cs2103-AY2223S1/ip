package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This (abstract) command encapsulates a Task object and inserts it into
 * the task list when executed.
 * <p>
 * This abstract class provides the skeletal implementation of a "create new Task"-type command
 * and should be the superclass of all "create new Task"-type commands.
 */
public abstract class TaskCommand extends Command {

    private final Task newTask;

    /**
     * Constructs TaskCommand with the specified Task.
     *
     * @param newTask the specified Task parameter.
     */
    public TaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Inserts the Task encapsulated by this onto the specified TaskList parameter.
     * <p>
     * In addition, the appropriate response will be sent to the specified Ui parameter
     * and the specified Storage parameter will also be updated with the new TaskList values.
     *
     * @param tasks the specified TaskList parameter.
     * @param ui the specified Ui parameter.
     * @param storage the specified Storage parameter.
     * @throws DukeException if tasks are unable to be saved into Storage.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.showReply(String.format("Gotcha! I added the following task to the list:\n"
                        + "  %s\n"
                        + "Currently, I have %d tasks recorded",
                newTask, tasks.getLength()));
        storage.save(tasks);
    }

    /**
     * Returns false as TaskCommand is not a terminating Command.
     *
     * @return false.
     */
    @Override
    public boolean isTerminator() {
        return false;
    }
}

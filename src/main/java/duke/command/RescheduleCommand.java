package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A rescheduleCommand that encapsulates the operation of rescheduling a specified task;
 */
public class RescheduleCommand extends Command {

    private static final int OFFSET = -1;
    private int indexOfTaskToReschedule;

    private final Task rescheduledTask;

    /**
     * Creates a RescheduleCommand with index of the task specified.
     *
     * @param indexOfTaskToReschedule index
     * @param rescheduledTask task
     */
    public RescheduleCommand(int indexOfTaskToReschedule, Task rescheduledTask) {
        this.indexOfTaskToReschedule = indexOfTaskToReschedule;
        this.rescheduledTask = rescheduledTask;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {

        indexOfTaskToReschedule += OFFSET;

        isValidIndex(tasks);

        Task beingRescheduled = tasks.getTasks().get(indexOfTaskToReschedule);
        tasks.reschedule(indexOfTaskToReschedule, rescheduledTask);
        storage.save(tasks);
        ui.showRescheduledMessage(beingRescheduled, rescheduledTask);
    }

    private void isValidIndex(TaskList tasks) throws InvalidIndexException {
        if (indexOfTaskToReschedule <= 0 || indexOfTaskToReschedule > tasks.getNumOfRemainingTasks()) {
            throw new InvalidIndexException();
        }
    }
}

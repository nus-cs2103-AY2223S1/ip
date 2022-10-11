package isara.command;

import java.io.File;
import java.time.LocalDate;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;
import isara.task.Deadline;
import isara.task.Task;

/**
 * Class to represent the Reschedule Command.
 */
public class RescheduleCommand extends Command {
    private int taskIndex;
    private LocalDate date;

    /**
     * Constructor for the RescheduleCommand.
     *
     * @param taskIndex The index of the task to be rescheduled.
     * @param date The date to which the task will be rescheduled to.
     */
    public RescheduleCommand(int taskIndex, LocalDate date) {
        super(false);
        this.taskIndex = taskIndex;
        this.date = date;
    }

    /**
     * Updates the task's deadline (/by) to the new date. Marks
     * the new task as done if it is previously marked.
     *
     * @param tasks The TaskList containing the tasks.
     * @param task The task to be updated.
     * @return The updated task.
     */
    public Task updateTask(TaskList tasks, Task task) {
        String taskName = task.getTaskName();
        String statusIcon = task.getStatusIcon();
        Task updatedTask = new Deadline(taskName, date);
        if (statusIcon.equals("X")) {
            updatedTask.mark();
        }
        tasks.set(taskIndex, updatedTask);
        return updatedTask;
    }

    /**
     * Reschedules the task's deadline to a new date.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return The message that is printed when a task is rescheduled.
     * @throws IsaraException The exception that is thrown if the task index is not valid or the task
     *     is not a Deadline.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) throws IsaraException {
        Ui ui = new Ui();
        int taskIndexRef = taskIndex + 1;
        int amountOfTasks = tasks.getNumberOfTasks();
        boolean isTaskIndexValid = taskIndexRef <= amountOfTasks;
        if (!isTaskIndexValid) {
            throw new IsaraException("☹ OOPS!!! Looks like the task you're looking for does not exist :-(");
        }

        Task task = tasks.getTask(taskIndex);
        if (task instanceof Deadline) {
            Task updatedTask = updateTask(tasks, task);
            return ui.reschedule(updatedTask);
        } else {
            throw new IsaraException("☹ OOPS!!! I'm so sorry :-( you can only reschedule deadlines.");
        }
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;

/**
 * Command to mark tasks as done.
 */
public class SnoozeCommand extends Command {

    private int num;
    private String newDate;

    /**
     * Constructor for MarkCommand.
     *
     * @param info type of command
     * @param num index of task
     */
    public SnoozeCommand(String info, int num, String newDate) {
        super(info);
        this.num = num;
        this.newDate = newDate;
    }

    /**
     * Executes the snooze command by changing the date to a later date.
     * for task at index num to true.
     *
     * @param ui Ui to show mark operation messages
     * @param taskList TaskList to execute mark command
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        assert(ui != null && taskList != null);
        Task task;
        try {
            task = taskList.getTask(num);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.showInvalidIndexMessage());
        }
        TaskType taskType = task.getTaskType();
        if (taskType == TaskType.DEADLINE) {
            Deadline deadline = (Deadline) task;
            deadline.setByTime(newDate);
        } else if (taskType == TaskType.EVENT) {
            Event event = (Event) task;
            event.setAtTime(newDate);
        } else {
            throw new DukeException(ui.invalidSnoozeTaskMessage());
        }
        storage.writeFile(taskList);
        return ui.showSnoozeMessage(task);
    }
}

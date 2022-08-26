package duke.command;

import duke.DukeException;
import duke.storage.TaskRecords;
import duke.task.Task;
import duke.ui.BotUI;

/**
 * Represents a marking command of task. A <code>DeleteCommand</code> object stores
 * the details of the task as a String type Integer. eg. "1"
 *
 */
public class MarkCommand extends Command {
    private final String details;

    /**
     * Constructs MarkCommand object
     *
     * @param command command of the user input
     * @param details details of the user input as String type Integer
     */
    public MarkCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    /**
     * Marks or Un-marks Tasks from the TaskRecords
     *
     * @param taskList stores the list of tasks
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface
     * @throws DukeException - thrown when NumberFormatException or IndexOutOfBoundsException is
     *     catch cause by invalid user input. e.g. delete1 or delete someNonIntegerText.
     */
    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(details) - 1;
            Task currTask = taskList.get(taskIdx);
            currTask = (super.getCommand().equals("mark")) ? currTask.markDone() : currTask.unmarkDone();
            taskList.addProcess(currTask);
            ui.informMarkStatus(taskList.get(taskIdx));
        } catch (NumberFormatException ex) {
            throw new DukeException(ui.invalidCheckFormat());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.taskNotExist(taskList));
        }
    }

    /**
     * Returns the true/false of the command exit status that
     * will cause duke stop running
     *
     * @return the true/false of the command exit status
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

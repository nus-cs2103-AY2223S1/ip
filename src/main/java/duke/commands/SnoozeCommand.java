package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.exceptions.DukeNoDateTimeException;
import duke.gui.GuiText;
import duke.tasks.Task;
import duke.tasks.TaskWithDateTime;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This class tells Duke to snooze the task by 1 day.
 */
public class SnoozeCommand implements Command {

    /** The index of the task to snooze. */
    private int index;

    /**
     * Constructs a SnoozeCommand object.
     *
     * @param index The index of the task to snooze.
     */
    public SnoozeCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the snooze command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        Task task = taskList.getTask(index);
        if (task instanceof TaskWithDateTime) {
            TaskWithDateTime taskDateTime = (TaskWithDateTime) task;
            LocalDateTime newDateTime = taskDateTime.getDateTime().plusDays(1);
            taskDateTime.setDateTime(newDateTime);
            storage.writeToFile(taskList);
            return GuiText.formatSnooze(index, ((TaskWithDateTime) task));
        } else {
            throw new DukeNoDateTimeException();
        }
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SnoozeCommand) {
            SnoozeCommand that = (SnoozeCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}

package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tasks.Deadline;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This class tells Duke to store a new task with a deadline.
 */
public class DeadlineCommand implements Command {

    /** The deadline task to add. */
    private Deadline deadline;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param desc The description of the deadline task.
     * @param dateTime The due date and time of the task.
     */
    public DeadlineCommand(String desc, LocalDateTime dateTime) {
        deadline = new Deadline(desc, dateTime);
    }

    /**
     * Executes the deadline command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        taskList.addTask(deadline);
        storage.appendToFile(deadline);
        return GuiText.formatAddTaskString(taskList.getSize() - 1, deadline);
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadlineCommand) {
            DeadlineCommand that = (DeadlineCommand) o;
            if (deadline.equals(that.deadline)) {
                return true;
            }
        }
        return false;
    }
}

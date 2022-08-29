package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

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
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(deadline);
            storage.appendToFile(deadline);
            ui.sayAddTask(deadline);
            ui.sayTaskListSize(taskList);
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
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
        if (o instanceof DeadlineCommand) {
            DeadlineCommand that = (DeadlineCommand) o;
            if (deadline.equals(that.deadline)) {
                return true;
            }
        }
        return false;
    }
}

package duke.commands;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs create a deadline task with specified description
 * and date command.
 */
public class DeadlineCommand implements Command {
    /** Deadline task to be added into TaskList */
    private Deadline deadline;

    /**
     * Constructor for class DeadlineCommand.
     *
     * @param description Description of deadline.
     * @param date Due date of deadline.
     */
    public DeadlineCommand(String description, LocalDate date) {
        this.deadline = new Deadline(description, false, date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(deadline);
        storage.appendToFile(deadline);
        return Ui.formatAddTaskString(deadline);
    }
}

package duke.commands;

import java.time.LocalDate;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;

/**
 * This class performs create a deadline task with specified description and date to add to
 * TaskList command.
 */
public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
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
        return Ui.formatAddTaskMessage(deadline);
    }
}

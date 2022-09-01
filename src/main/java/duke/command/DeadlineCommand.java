package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * DeadlineCommand class represents the deadline command given by user.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate deadline;

    /**
     * Constructor for the DeadlineCommand class.
     * Sets the description and deadline to the local variables.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Adds the deadline task to the taskList.
     * Returns the message that the deadline task was added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @return The message that deadline task was added.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Deadline deadline = new Deadline(this.description, this.deadline);
        taskList.add(deadline,storage);
        String message = "Nice! This task has been successfully added!";
        return ui.displayCommandMessage(message, deadline, taskList.getSize());
    }
}

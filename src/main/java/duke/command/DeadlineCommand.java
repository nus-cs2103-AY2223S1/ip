package duke.command;

import java.time.LocalDate;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

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
     * @param commandHistory History of commands made.
     * @return The message that deadline task was added.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        Deadline deadline = new Deadline(this.description, this.deadline);
        commandHistory.addCommand(this);
        taskList.add(deadline, storage);
        String message = "Nice! This task has been successfully added!";
        return ui.displayCommandMessage(message, deadline, taskList.getSize());
    }

    /**
     * Removes the deadline that has just been added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the deadline has been removed.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        Deadline deletedDeadline = new Deadline(this.description, this.deadline);
        taskList.remove(taskList.getSize() - 1, storage);
        String message = "This deadline is no longer added!";
        return ui.displayCommandMessage(message, deletedDeadline, taskList.getSize());
    }
}

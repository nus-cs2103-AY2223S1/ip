package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeadlineCommand is a Command that creates a new Deadline.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate date;

    /**
     * A constructor for DeadlineCommand.
     *
     * @param description The description of the Deadline.
     * @param date The date of when the Deadline is due.
     */
    public DeadlineCommand(String description, LocalDate date) {
        super(false);
        this.description = description;
        this.date = date;
    }

    /**
     * A method that creates a new Deadline and adds it to the TaskList, displays the add-message, and updates the
     * Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(description, date);
        int size = tasks.getSize();
        ui.showAdd(tasks.getTask(size - 1), size);
        storage.save(tasks.saveToStorage());
    }
}

package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * EventCommand is a Command that creates a new Event.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class EventCommand extends Command {
    private String description;
    private LocalDate date;

    /**
     * A constructor for EventCommand.
     *
     * @param description The description of the Event.
     * @param date The date of when the Event is at.
     */
    public EventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * A method that creates a new Event and adds it to the TaskList, displays the add-message, and updates the Storage.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return The add-message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(description, date);
        int size = tasks.getSize();
        storage.save(tasks.saveToStorage());
        return ui.showAdd(tasks.getTask(size - 1), size);
    }
}

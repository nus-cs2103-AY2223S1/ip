package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Response;
import duke.util.Storage;
import duke.util.Success;

/**
 * Represents the command that is executed when the user inputs event.
 *
 * @author njxue
 * @version v0.1
 */
public class EventCommand extends Command {
    /** Description of the event task. */
    private String description;

    /** Date and time of the event task. */
    private LocalDateTime atDateTime;

    /**
     * Creates an EventCommand.
     *
     * @param description Description of the event task.
     * @param atDateTime Date and time of the event task.
     */
    public EventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    /**
     * Returns the format of the event command.
     *
     * @return The format of the event command.
     */
    public static String getFormat() {
        return "event <String> /at <String>";
    }

    /**
     * Executes the event command. Creates and adds a new event task.
     *
     * @param tasks TaskList the newly created event task should be added to.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @return A Success Response.
     * @throws DukeException If storage object is unable to save the list of tasks, or if TaskList cannot be properly
     *              sorted.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(description, atDateTime);
        tasks.add(event);
        tasks.sort();
        String message = ui.taskAddedMessage(event, tasks);
        storage.save(tasks);
        return new Success(message);
    }

    /**
     * Returns false, because event is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

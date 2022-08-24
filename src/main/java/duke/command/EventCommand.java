package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs <code>event</code>.
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
     * Creates an <code>EventCommand</code>.
     *
     * @param description Description of the event task.
     * @param atDateTime Date and time of the event task.
     */
    public EventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    /**
     * Returns the format of the <code>event</code> command.
     *
     * @return The format of the <code>event</code> command.
     */
    public static String getFormat() {
        return "event <String> /at <String>";
    }

    /**
     * Executes the <code>event</code> command. 
     * Creates and adds a new event task.
     *
     * @param tasks <code>TaskList</code> the newly created event task should be added to.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the newly created event task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(description, atDateTime);
        tasks.add(event);
        ui.showAddTask(event, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because <code>event</code> is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

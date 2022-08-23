package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs <code>deadline</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class DeadlineCommand extends Command {
    
    /** Description of the deadline task. */
    private final String description;
    
    /** Deadline of the deadline task. */
    private final LocalDateTime byDateTime;

    /**
     * Creates a <code>DeadlineCommand</code>.
     * 
     * @param description Description of the deadline task.
     * @param byDateTime Deadline of the deadline task.
     */
    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    /**
     * Returns the format of the <code>deadline</code> command.
     * 
     * @return The format of the <code>deadline</code> command.
     */
    public static String getFormat() {
        return "deadline <String> /by <String>";
    }

    /**
     * Executes the <code>deadline</code> command. 
     * Creates and adds a new deadline task.
     *
     * @param tasks <code>TaskList</code> which the newly created deadline task should be added into.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the newly created deadline task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deadline = new Deadline(description, byDateTime);
        tasks.add(deadline);
        ui.showAddTask(deadline, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because <code>deadline</code> is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

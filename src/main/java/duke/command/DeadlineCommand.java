package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Response;
import duke.util.Storage;
import duke.util.Success;

/**
 * Represents the command that is executed when the user inputs deadline.
 *
 * @author njxue
 * @version v0.1
 */
public class DeadlineCommand extends Command {
    /** Description of the deadline task. */
    private String description;

    /** Deadline of the deadline task. */
    private LocalDateTime byDateTime;

    /**
     * Creates a DeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param byDateTime Deadline of the deadline task.
     */
    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    /**
     * Returns the format of the deadline command.
     *
     * @return The format of the deadline command.
     */
    public static String getFormat() {
        return "deadline <String> /by <String>";
    }

    /**
     * Executes the deadline command. Creates and adds a new deadline task.
     *
     * @param tasks TaskList which the newly created deadline task should be added into.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @return A Success Response.
     * @throws DukeException If storage object is unable to save the list of tasks, or if TaskList cannot be properly
     *              sorted.
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deadline = new Deadline(description, byDateTime);
        tasks.add(deadline);
        tasks.sort();
        String message = ui.taskAddedMessage(deadline, tasks);
        storage.save(tasks);
        return new Success(message);
    }

    /**
     * Returns false, because deadline is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

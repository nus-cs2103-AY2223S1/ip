package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to execute adding a Deadline to a TaskList
 *
 * @author Nephelite
 * @version 0.2
 */
public class DeadlineCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of a DeadlineCommand
     *
     * @param command
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public DeadlineCommand(String command, TaskList tasks, Ui ui) {
        assert(command != null && tasks != null && ui != null);
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException if the input command is invalid
     * @since 0.3
     */
    @Override
    public String execute(Storage storage) throws DukeException {
        String[] returnedArray = command.split(" /by ", 2);
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete."
                    + "\nPlease use the [help] command to check the proper usage of [deadline].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/by] component, or the "
                    + "second half of the command."
                    + "\nPlease use the [help] command to check the proper usage of [deadline].");
        }
        String response = addDeadLineGetResponse(returnedArray[0], returnedArray[1]);
        storage.saveDuke(tasks);
        return response;
    }

    /**
     * Adds deadline to tasks and returns Duke's response
     *
     * @param task
     * @param deadlineDate
     * @return Duke's response to adding a Deadline
     * @since 0.3
     */
    private String addDeadLineGetResponse(String task, String deadlineDate) {
        Deadline deadline = new Deadline(task, deadlineDate);
        tasks.add(deadline);
        return ui.addTask(deadline, tasks.size());
    }

    /**
     * {@inheritDoc}
     *
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to execute adding an Event to a TaskList
 *
 * @author Nephelite
 * @version 0.2
 */
public class EventCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for an EventCommand
     *
     * @param command the command
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public EventCommand(String command, TaskList tasks, Ui ui) {
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
     * @throws DukeException for invalid inputs
     * @since 0.3
     */
    @Override
    public String execute(Storage storage) throws DukeException {
        String[] returnedArray = command.split(" /at ", 2);
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete."
                    + "\nPlease use the [help] command to check the proper usage of [event].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/at] component,"
                    + " or the second half ot the command."
                    + "\nPlease use the [help] command to check the proper usage of [event].");
        }
        String response = addEventGetResponse(returnedArray[0], returnedArray[1]);
        storage.saveDuke(tasks);
        return response;
    }

    /**
     * Adds event to tasks and returns Duke's response
     *
     * @param task
     * @param date
     * @return Duke's response to adding a Deadline
     * @since 0.3
     */
    private String addEventGetResponse(String task, String date) {
        Event event = new Event(task, date);
        tasks.add(event);
        return ui.addTask(event, tasks.size());
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

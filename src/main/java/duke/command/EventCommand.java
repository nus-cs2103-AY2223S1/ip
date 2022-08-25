package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Command to execute adding an Event to a TaskList
 * @author Nephelite
 * @version 0.1
 */
public class EventCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for an EventCommand
     * @param command the command
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public EventCommand(String command, TaskList tasks, Ui ui) {
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * {@inheritDoc}
     * @param storage Duke's storage system for tasks
     * @throws DukeException
     */
    @Override
    public void execute(Storage storage) throws DukeException {
        String[] returnedArray = command.split(" /at ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your duke.command is incomplete."
                    + "\nPlease use the [help] duke.command to check the proper usage of [event].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your duke.command is missing the [/at] component,"
                    + " or the second half ot the duke.command."
                    + "\nPlease use the [help] duke.command to check the proper usage of [event].");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Event event = new Event(returnedArray[0], returnedArray[1]);
        tasks.add(event);
        ui.addTask(event, tasks.size());
        storage.saveDuke(tasks);
    }

    /**
     * {@inheritDoc}
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

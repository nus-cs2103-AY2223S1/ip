package command;

import exceptions.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.UI;

/**
 * Command that handles adding Event to TaskList and Storage.
 */
public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Runs when event is to be added.
     *
     * @param taskList TaskList to append Event to.
     * @param ui ui provides user command.
     * @param storage Storage space to append Deadline to.
     * @throws DukeException When parsing user command fails.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String storableLine = event + "\n";
        if (storage.isLineAppended(storableLine)) {
            taskList.addTask(event);
            ui.showMessage("Added event");
        }
    }
}

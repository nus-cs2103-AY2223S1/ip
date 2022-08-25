package command;

import exceptions.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Command that handles adding Event to TaskList and Storage.
 */
public class AddEventCommand extends Command {

    /**
     * Runs when event is to be added.
     * @param taskList TaskList to append Event to.
     * @param ui ui provides user command.
     * @param storage Storage space to append Deadline to.
     * @throws DukeException When parsing user command fails.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String userInput = ui.getCurrentInput();
        Event eventToAdd = Parser.stringToEvent(userInput);
        taskList.addEvent(eventToAdd);
        String storableLine = eventToAdd.toString() + "\n";
        storage.appendLine(storableLine);
        ui.showMessage("Added event");
    }

    /**
     * Returns false to allow program execution to continue.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package command;

import exceptions.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;
import utility.StorageParser;

public class AddEventCommand extends Command {
    public AddEventCommand() {
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String userInput = ui.currentInput;
        Event eventToAdd = Parser.stringToEvent(userInput);
        String storableLine = StorageParser.storableDescription(eventToAdd);
        storage.appendLine(storableLine);
        taskList.addEvent(eventToAdd);
        ui.showMessage("added event");
    }

    public boolean isExit() {
        return false;
    }
}

package command;

import exceptions.DukeException;
import storage.Storage;
import task.Event;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;


public class AddEventCommand extends Command {
    public AddEventCommand() {
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String userInput = ui.currentInput;
        Event eventToAdd = Parser.stringToEvent(userInput);
        taskList.addEvent(eventToAdd);
        String storableLine = eventToAdd.toString() + "\n";
        storage.appendLine(storableLine);
        ui.showMessage("Added event");
    }

    public boolean isExit() {
        return false;
    }
}

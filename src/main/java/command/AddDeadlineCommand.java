package command;

import exceptions.DukeException;
import storage.Storage;
import task.Deadline;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;
import utility.StorageParser;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand() {
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String userInput = ui.currentInput;
        Deadline deadlineToAdd = Parser.stringToDeadline(userInput);
        String storableLine = StorageParser.storableTaskDescription(deadlineToAdd);
        storage.appendLine(storableLine);
        taskList.addDeadline(deadlineToAdd);
        ui.showMessage("added deadline");
    }

    public boolean isExit() {
        return false;
    }
}

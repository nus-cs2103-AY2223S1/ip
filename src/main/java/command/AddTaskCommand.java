package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import task.Task;
import utility.Parser;
import utility.StorageParser;

import java.text.Normalizer;

public class AddTaskCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String userInput = ui.currentInput;
        Task taskToAdd = Parser.stringToTask(userInput);
        String storableLine = StorageParser.storableDescription(taskToAdd);
        storage.appendLine(storableLine);
        taskList.addTask(taskToAdd);
        ui.showMessage("Added task");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

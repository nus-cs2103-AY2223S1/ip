package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Command that represents a
 * find operation on TaskList.
 */
public class FindCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String findString = Parser.stringToFind(ui.getCurrentInput());
        TaskList matchedTasks = taskList.findTasks(findString);
        ListCommand listCommand = new ListCommand();
        listCommand.execute(matchedTasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

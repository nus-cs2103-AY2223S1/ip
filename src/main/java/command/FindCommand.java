package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;


public class FindCommand extends Command  {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String findString = Parser.stringToFind(ui.currentInput);
        System.out.println(taskList.findTasks(findString).get(1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

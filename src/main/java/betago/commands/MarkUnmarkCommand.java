package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

public class MarkUnmarkCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.markUnmarkItems(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

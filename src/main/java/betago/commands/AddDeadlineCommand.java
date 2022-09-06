package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

public class AddDeadlineCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.addDeadline(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

public class FindCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.findTasks(str);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

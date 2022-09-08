package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;
import task.Todo;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FindCommand extends Command {

    String str;

    public FindCommand(String str) {
        this.str = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String sub = str.substring(4).trim();
        if (!sub.isEmpty()) {
            StringBuilder output = new StringBuilder();
            output.append("Here are the matching tasks in your list:\n");
            output.append(tasks.filterToString(sub));
            return output.toString();
        } else {
            throw new DukeException("Please put in an argument for the find command.");
        }
    }
}

package drake.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

/**
 * Represent a list command.
 */
public class ListCommand extends Command {
    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        ArrayList<String> reply = new ArrayList<>();
        reply.add("Here are the tasks in your list:");
        for (int i = 1; tasks.isValidTaskNumber(i); i++) {
            reply.add(i + ". " + tasks.getTaskToString(i));
        }
        return reply;
    }
}

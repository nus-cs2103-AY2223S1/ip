package duke.commands;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Storage;

import java.util.List;

public class FindCommand extends Command {
    private final String[] arguments;

    public FindCommand(String[] args) {
        this.arguments = args;
    }

    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Please enter a keyword to search!");
        } else if (arguments.length > 2) {
            throw new DukeException("You can only state one keyword");
        }

        String keyword = arguments[1].toUpperCase();
        String response;
        boolean isNullList = true;

        List<Task> matchingTasks = tl.findTasks(keyword);
        for (Task t : matchingTasks) {
            if (t != null) {
                isNullList = false;
            }
        }
        if (matchingTasks.isEmpty() || isNullList) {
            response = "There is no matching tasks with your keyword";
        } else {
            response = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task curTask = matchingTasks.get(i);
                if (curTask != null) {
                    response = response.concat((i + 1) + "." + curTask.toString() + "\n");
                }
            }
        }
        return response;
    }
}

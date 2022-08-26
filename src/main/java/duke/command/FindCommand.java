package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the command to find matching tasks from the list.
 */
public class FindCommand extends Command {
    /** The keyword to search the list of tasks for. */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //todo
        ArrayList<Task> foundTasks = tasks.findTask(this.keyword);

        String matchingTasks = "";

        for (Task task : foundTasks) {
            matchingTasks += "\n" + task;
        }

        ui.botReply("Here are the matching tasks in your list:" + matchingTasks);
    }
}

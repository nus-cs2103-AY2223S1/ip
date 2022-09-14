package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.response.Response;

import java.util.ArrayList;

public class FindCommand extends Command {
    /** String to be used to find matching tasks. */
    private String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param keyword String to be used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(this.keyword);
        return response.foundTasksString(foundTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

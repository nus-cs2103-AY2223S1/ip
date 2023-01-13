package kirby.commands;

import java.util.ArrayList;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyInvalidArgumentException;
import kirby.tasks.Task;
import kirby.time.HandleTime;
import kirby.ui.Ui;

/**
 * GetCommand class handles the command to get all the tasks on a specified date.
 */
public class GetCommand extends Command {
    private ArrayList<Task> taskFetched;
    private final String argument;

    /**
     * Constructor for the class GetCommand.
     *
     * @param argument Argument of a command.
     */
    public GetCommand(String argument) throws KirbyInvalidArgumentException {
        this.taskFetched = null;
        if (argument == null || !HandleTime.isValidDate(argument)) {
            throw new KirbyInvalidArgumentException("get");
        }
        this.argument = argument;
    }

    /**
     * {@inheritDoc}
     * Lists down the list of tasks on a specified date if the date is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        taskFetched = HandleTime.getTaskByDate(tasks.getList(), argument);
        if (taskFetched.size() < 1) {
            return "No tasks found!";
        }
        return tasks.findTaskString(taskFetched);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

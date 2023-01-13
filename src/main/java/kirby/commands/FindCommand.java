package kirby.commands;

import java.util.ArrayList;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;
import kirby.ui.Ui;

/**
 * FindCommand class handles the command to find the list of tasks that contains a specified keyword.
 */
public class FindCommand extends Command {
    private ArrayList<Task> tasksFound;
    private final String argument;

    /**
     * Constructor for the class FindCommand.
     *
     * @param argument Arguments of a command.
     */
    public FindCommand(String argument) throws KirbyMissingArgumentException {
        this.tasksFound = null;
        if (argument == null) {
            throw new KirbyMissingArgumentException("find");
        }
        this.argument = argument;
    }

    /**
     * {@inheritDoc}
     * Lists down the list of tasks that contains the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasksFound = tasks.findTask(argument);
        if (tasksFound.size() < 1) {
            return "No tasks found!";
        }
        return tasks.findTaskString(tasksFound);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

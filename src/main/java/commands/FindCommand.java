package commands;

import byu.TaskList;
import byu.Ui;

/**
 * A command to find tasks with names containing a given string.
 */
public class FindCommand extends Command {

    private final String substring;

    /**
     * Creates a FindCommand with a given string.
     *
     * @param substring the substring used to find tasks with names containing it.
     */
    public FindCommand(String substring) {
        this.substring = substring;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.find(this.substring);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

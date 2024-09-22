package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Executes the command to list all tasks.
 *
 * @author Lim Ai Lin
 */
public class ListCommand extends Command {

    /**
     * Executes the list command the user inputs.
     *  @param tasks The list containing all the tasks and notes to be printed.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage containing all tasks and notes the user has previously input.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }
}

package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    /*
     * Constructs an UnmarkCommand object.
     */
    public UnmarkCommand(String instruction) {
        super(instruction);
    }

    /*
     * Executes the command.
     * 
     * @param tasks The task list.
     * 
     * @param ui The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(instruction.substring(7)) - 1;
            tasks.get(index).setUndone();
            storage.saveList(tasks);
            return ui.showUnmarkMessage(tasks.get(index));
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}

package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /*
     * Constructs a ListCommand object.
     * 
     * @param instruction The instruction to be executed.
     */
    public ListCommand(String instruction) {
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
        return ui.showArray(tasks);
    }
}

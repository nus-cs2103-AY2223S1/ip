package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Represents a command to find tasks according to user input.
 */
public class FindCommand extends Command {
    public FindCommand(String instruction) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(ui.showMatchingTasks(tasks.find(instruction)));
    }
}

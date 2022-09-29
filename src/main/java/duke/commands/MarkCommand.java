package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /*
     * Constructs a MarkCommand object.
     * 
     * @param instruction The instruction to be executed.
     */
    public MarkCommand(String instruction) {
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
            int index = Integer.parseInt(instruction.substring(5)) - 1;
            tasks.get(index).setDone();
            storage.saveList(tasks);
            return ui.showMarkMessage(tasks.get(index));
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
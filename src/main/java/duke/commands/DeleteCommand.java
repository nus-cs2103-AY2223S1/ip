package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/*
 * Represents a command to delete a task to the task list.
 */
public class DeleteCommand extends Command {
    /*
     * Constructs a DeleteCommand object.
     */
    public DeleteCommand(String instruction) {
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
        try {
            int index = Integer.parseInt(instruction) - 1;
            Task deleted = tasks.get(index);
            tasks.delete(index);
            ui.print(ui.showDeleteMessage(deleted));
            storage.saveList(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.print(ui.showError(new DukeException(e.getMessage())));
        } catch (DukeException e) {
            ui.print(ui.showError(e));
        }
    }
}

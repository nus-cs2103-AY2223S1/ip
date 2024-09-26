package rick.commands;

import rick.RickException;
import rick.Storage;
import rick.Ui;
import rick.tasks.TaskList;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an UnmarkCommand object.
     */
    public UnmarkCommand(String instruction) {
        super(instruction);
    }

    /**
     * Executes the command.
     * 
     * @param tasks   The task list.
     * 
     * @param ui      The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(instruction) - 1;
            tasks.get(index).setUndone();
            storage.saveList(tasks);
            return ui.showUnmarkMessage(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            return ui.showError(new RickException("Morty! You entered an invalid index!\n"));
        } catch (RickException e) {
            return ui.showError(e);
        } catch (Exception e) {
            return ui.showError(new RickException("Morty! You must enter a valid index number!\n"
                    + e.getMessage()));
        }
    }
}

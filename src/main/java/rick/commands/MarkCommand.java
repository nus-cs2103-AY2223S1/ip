package rick.commands;

import rick.RickException;
import rick.Storage;
import rick.Ui;
import rick.tasks.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a MarkCommand object.
     * 
     * @param instruction The instruction to be executed.
     */
    public MarkCommand(String instruction) {
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
            tasks.get(index).setDone();
            storage.saveList(tasks);
            return ui.showMarkMessage(tasks.get(index));
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
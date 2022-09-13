package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Task;

/**
 * Represents command that marks tasks as completed. A <code>MarkCommand</code> object corresponds to
 * an instruction to mark completion by index e.g., <code>mark 2</code>.
 */
public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String fullCommand) {
        this.input = fullCommand;
    }

    /**
     * Marks a task as completed, saves the new task if valid, else throw exception.
     * Informs the user about the situation through String output.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     * @throws PikachuException If invalid format or out of range of the task index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        boolean isValidNumber = Pikachu.isNumeric(input.substring(5));
        boolean isWithinRange = Integer.parseInt(input.substring(5)) <= tasks.getTaskList().size()
                && Integer.parseInt(input.substring(5)) > 0;
        if (!isValidNumber) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (!isWithinRange) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(5));
            Task task = tasks.getTaskList().get(temp - 1);
            task.setDone(true);
            storage.save(tasks.getTaskList());
            return "Pi-ka(Done): " + task;
        }
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
}

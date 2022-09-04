package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Task;

/**
 * Represents command that unmarks tasks as completed. A <code>UnmarkCommand</code> object corresponds to
 * an instruction to unmark completion by index e.g., <code>mark 2</code>.
 */
public class UnmarkCommand extends Command {
    private final String input;

    public UnmarkCommand(String fullCommand) {
        this.input = fullCommand;
    }

    /**
     * Marks a task as incomplete, saves the new task if valid, else throw exception.
     * Informs the user about the situation through String output.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @throws PikachuException If invalid format or out of range of the task index.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        if (!Pikachu.isNumeric(input.substring(7))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(7)) > tasks.getTaskList().size()
                || Integer.parseInt(input.substring(7)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.getTaskList().get(temp - 1);
            task.setDone(false);
            System.out.println("Pipi-ka(Undone): " + task);
        }
        storage.save(tasks.getTaskList());
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
}

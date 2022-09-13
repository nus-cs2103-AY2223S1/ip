package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Task;

/**
 * Represents command that performs delete function. A <code>DeleteCommand</code> object corresponds to
 * an instruction to delete tasks e.g., <code>delete 2</code>
 */
public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String fullCommand) {
        this.input = fullCommand;
    }

    /**
     * Deletes the task and saves the update to task list if valid, else throw exception.
     * Informs the user about the situation through String output.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     * @throws PikachuException If invalid format or the index of task out of range.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        boolean isValidNumber = Pikachu.isNumeric(input.substring(7));
        boolean isWithinRange = Integer.parseInt(input.substring(7)) <= tasks.getTaskList().size()
                && Integer.parseInt(input.substring(7)) > 0;
        
        assert input.startsWith("delete ");
        
        if (!isValidNumber) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (!isWithinRange) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.getTaskList().get(temp - 1);
            tasks.getTaskList().remove(temp - 1);
            storage.save(tasks.getTaskList());
            return "Pi-ka(Removed): " + task + '\n' + "Pikaaaaa: " + tasks.getTaskList().size()
                    + (tasks.getTaskList().size() > 1 ? " tasks" : " task");
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

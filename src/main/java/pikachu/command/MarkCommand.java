package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

/**
 * Represents command that marks certain tasks as completed according to index. A <code>MarkCommand</code> object corresponds to
 * an instruction to mark completion by index e.g., <code>mark 2</code>
 */
public class MarkCommand extends Command {
    String input;

    public MarkCommand(String fullCommand) {
        this.input = fullCommand;
    }

    /**
     * Marks a task as completed, saves the new task if valid, else throw exception.
     * Informs the user about the situation through String output
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @throws PikachuException If invalid format or out of range of the task index
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        if (!Pikachu.isNumeric(input.substring(5))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(5)) > tasks.taskList.size() || Integer.parseInt(input.substring(5)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(5));
            Task task = tasks.taskList.get(temp - 1);
            task.setDone(true);
            System.out.println("Pi-ka(Done): " + task);
        }
        storage.save(tasks.taskList);
    }

    /**
     * Returns whether this function performs an exit action on the task manager
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
    
}

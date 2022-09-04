package pikachu.command;

import pikachu.Pikachu;
import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

/**
 * Represents command that performs delete function to the to do list. A <code>DeleteCommand</code> object corresponds to
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
     * @throws PikachuException If invalid format or the index of task out of range.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException{
        if (!Pikachu.isNumeric(input.substring(7))) {
            throw new PikachuException("Pi-must be numbers behind-pi!");
        } else if (Integer.parseInt(input.substring(7)) > tasks.taskList.size() || Integer.parseInt(input.substring(7)) <= 0) {
            throw new PikachuException("Pi-not within range-pi!");
        } else {
            int temp = Integer.parseInt(input.substring(7));
            Task task = tasks.taskList.get(temp - 1);
            tasks.taskList.remove(temp-1);
            System.out.println("Pi-ka(Removed): " + task + '\n' +"Pikaaaaa: " + tasks.taskList.size() + (tasks.taskList.size() > 1 ? " tasks" : " task"));
            storage.save(tasks.taskList);
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

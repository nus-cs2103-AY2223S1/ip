package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents the command for the addition of new tasks to Duke's TaskList.
 */
public class AddCommand implements Command {
    String[] inputs;
    String commandType;

    /**
     * Constructs an AddCommand.
     *
     * @param inputs An array of String input obtained from parsing the user input.
     */
    public AddCommand(String[] inputs) {
        this.inputs = inputs;
        commandType = inputs[0].toUpperCase();
    }

    /**
     * Executes the add command and prints the results of this add command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeException Exception from executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = Task.createTask(commandType, inputs);
        String res = tasks.add(task);
        storage.addTaskToStorage(task);
        assert !tasks.isEmpty();
        return res;
    }

}

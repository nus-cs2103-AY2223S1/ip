package duke.command;

import duke.exceptions.DukeException;
import duke.Parser;
import duke.Storage;
import duke.exceptions.DukeInvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;



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
     * Executes the add command and prints the results of this add command using Duke's Ui.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeException Exception from executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task;
        try {
            switch(commandType) {
            case "TODO":
                task = new ToDo(Parser.getDescription(inputs, null), false);
                break;
            case "DEADLINE":
                task = new Deadline(Parser.getDescription(inputs, "/by"),
                        false,
                        Parser.getTime(inputs, "/by"));
                break;
            case "EVENT":
                task = new Event(Parser.getDescription(inputs, "/at"),
                        false,
                        Parser.getTime(inputs, "/at"));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidDateException();
        }
        String res = tasks.add(task);
        storage.addTaskToStorage(task);
        assert !tasks.isEmpty();
        return res;
    }

}

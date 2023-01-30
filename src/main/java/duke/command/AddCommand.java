package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class to represent a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private String input;

    /**
     * Constructs an AddCommand object.
     *
     * @param storage  Storage class to be used
     * @param ui       UI class to be used
     * @param taskList TaskList that task is to be added to
     * @param input    String input from user
     */
    public AddCommand(Storage storage, Ui ui, TaskList taskList, String input) {
        super(storage, ui, taskList);
        this.input = input;
    }

    /**
     * Adds a task to the task list.
     *
     * @return String representation of the task added
     * @throws DukeException if the input is invalid
     */
    @Override
    public String execute() throws DukeException {
        return taskList.addTask(Task.createTask(input));
    }
}

package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final String input;

    /**
     * Constructs a command to delete a specified task.
     *
     * @param input The user input for the index of a task.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            ui.showDeleteTask(taskList.deleteTask(index), taskList);
        } catch (NumberFormatException e) {
            throw new DukeException("Input a valid number!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}

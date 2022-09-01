package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private String input;

    /**
     * Constructs a command to mark a specified task as done.
     *
     * @param input The user input for task number.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskList.get(index).markAsDone();
            ui.showMarkedTask(taskList.get(index));
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

package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructs command for marking a task as not done.
     *
     * @param input The index of the task.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskList.get(index).markAsUndone();
            ui.showUnmarkedTask(taskList.get(index));
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

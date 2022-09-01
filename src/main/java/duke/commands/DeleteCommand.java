package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            ui.showDeleteTask(taskList.deleteTask(index), taskList);
        } catch (NumberFormatException e) {
            throw new DukeException("Input a valid number!");
        }
    }

    public boolean isExit() {
        return false;
    }
}

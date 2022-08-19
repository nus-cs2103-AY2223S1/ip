package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.printMarkTask(taskList.markTask(index - 1));
            storage.save(taskList.getTasks());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}

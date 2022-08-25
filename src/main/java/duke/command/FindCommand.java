package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Finds task with specific word in tasklist
 */
public class FindCommand extends Command {
    private String text;

    public FindCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = ui.listToStringWithText(taskList.getList(), text);
        ui.printMessage(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

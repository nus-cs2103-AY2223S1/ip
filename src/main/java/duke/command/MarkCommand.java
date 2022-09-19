package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command{
    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        return taskList.get(index).mark();
    }
}

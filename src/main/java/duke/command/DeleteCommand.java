package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Parser;
import duke.StorageList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        int index = Parser.getIndex(ui.getLastInput());
        Task task = storageList.get(index);
        storageList.delete(index);
        Output.DELETE.modifyTask(task, storageList);
    }
}

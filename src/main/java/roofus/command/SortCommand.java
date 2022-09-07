package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

public class SortCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        String result = ui.sortList(taskList);
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return result;
    }
}

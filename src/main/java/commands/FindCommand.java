package commands;

import data.TaskList;
import exceptions.DukeException;
import storage.Storage;
import ui.Ui;

public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMultiMsg(tasks.findAndReturnStringArr(searchString));
    }
}

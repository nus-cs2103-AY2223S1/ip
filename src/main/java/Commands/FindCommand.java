package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public class FindCommand extends Command {
    private String txt;

    public FindCommand(String txt) {
        this.txt = txt;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String found = tasks.find(txt);
        ui.showFound(found);
    }
}

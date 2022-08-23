package commands;

import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class UpdateCommand extends Command {

    private String type;
    private int num;

    public UpdateCommand(String type, int num) {
        this.type = type;
        this.num = num - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (this.type) {
        case "mark":
            ui.showMark(tasks.mark(this.num));
            break;
        case "unmark":
            ui.showUnmark(tasks.unmark(this.num));
            break;
        }
        storage.updateStorage(tasks);
    }
}
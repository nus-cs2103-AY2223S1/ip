package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMark(num);
        ui.unMark(tasks, num);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand implements Command{
    private final int toMark;

    public MarkCommand(int toMark) {
        this.toMark = toMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.mark(toMark));
        storage.refresh(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

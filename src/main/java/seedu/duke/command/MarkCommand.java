package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.Objects;

public class MarkCommand extends Command {
    boolean toMark;
    int index;

    public MarkCommand(String mark, String index) {
        super(false);
        this.toMark = Objects.equals(mark, "mark");
        this.index = Integer.parseInt(index);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.toMark) {
            Task task = tasks.markTask(index);
            ui.showMarkStatus(task);
        } else {
            Task task = tasks.unmarkTask(index);
            ui.showUnmarkStatus(task);
        }
        storage.writeToFile(tasks);
    }
}

package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int t;

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(t);
    }
}

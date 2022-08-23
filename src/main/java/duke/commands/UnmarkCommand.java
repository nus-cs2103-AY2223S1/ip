package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command{
    private int t;

    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(int t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(t);
    }

}

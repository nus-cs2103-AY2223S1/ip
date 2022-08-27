package duke.commands;

import duke.*;
import duke.task.Task;

/**
 * The UnmarkCommand class represents user command unmark.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;

    /**
     * Constructor for UnmarkCommand that takes in
     * an integer representing Task to mark undone.
     * @param i Specified index.
     */
    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.unmarkDone(index);
        storage.save(tasks.getTaskList());
        ui.showUnmark(t);
    }
}

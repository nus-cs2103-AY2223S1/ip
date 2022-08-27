package duke.commands;

import duke.*;
import duke.task.Task;

/**
 * The MarkCommand class represents user command mark.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;

    /**
     * Constructor for MarkCommand that takes in
     * an integer representing Task to mark done.
     * @param i Specified index.
     */
    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markDone(index);
        storage.save(tasks.getTaskList());
        ui.showMark(t);
    }
}

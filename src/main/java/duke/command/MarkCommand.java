package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Executes the command to mark the input task
 * @author Jicson Toh
 */
public class MarkCommand extends Command {
    private final String action;

    /**
     * Creates a mark command object.
     * @param action user input action.
     */
    public MarkCommand(String action) {
        this.action = action;
    }

    /**
     * Executes the command to mark the task.
     *
     * @param tasks    amends task list if any.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage if any.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String i = action.substring(4);
            i = i.replaceAll(" ", "");
            int index = Integer.parseInt(i) - 1;
            tasks.markTaskStatus(index, true);
            ui.markedTask(true, tasks.getTaskString(index));
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Error: No Such Task :-(");
        }
    }

    /**
     * Returns true if exiting program.
     *
     * @return false if still running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

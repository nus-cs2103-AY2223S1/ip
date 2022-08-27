package duke.command;

import java.util.ArrayList;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.Task;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Executes the command to find a task
 * @author Jicson Toh
 */
public class FindCommand extends Command {
    private final String action;

    /**
     * Creates a find command object.
     * @param action user input action.
     */
    public FindCommand(String action) {
        this.action = action;
    }

    /**
     * Executes the command input.
     *
     * @param tasks   amends task list if any.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage if any.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> filteredTasks = new ArrayList<>();
            String keyword = action.substring(5).strip();
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                if (task.matchKeyword(keyword)) {
                    filteredTasks.add(task);
                }
            }
            if (filteredTasks.size() > 0) {
                ui.showFoundTasks(filteredTasks);
            } else {
                ui.noSuchTaskError();
            }
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

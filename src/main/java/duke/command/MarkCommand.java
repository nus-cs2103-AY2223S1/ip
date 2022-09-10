package duke.command;

import java.util.List;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to mark one or more tasks as done.
 */
public class MarkCommand extends Command {
    private List<Integer> indices;

    /**
     * Creates a MarkCommand.
     *
     * @param indices List of task IDs of tasks to be marked as done.
     */
    public MarkCommand(List<Integer> indices) {
        // sort from smallest to largest, so output is prettier
        indices.sort((c1, c2) -> c1 > c2 ? 1 : -1);
        this.indices = indices;
    }

    /**
     * Executes the MarkCommand to mark tasks identified by {@code}indices{@code} as done.
     *
     * @param tasks TaskList containing the task(s) to be marked as done.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String doneTasks = "";
        for (int index : indices) {
            String doneTask = tasks.markAsDone(index);
            doneTasks += doneTask + "\n";
        }
        storage.save(tasks);
        String plurality = indices.size() > 1 ? "these tasks" : "this task";
        return "I've marked " + plurality + " as done:\n" + doneTasks;
    }

    /**
     * Computes equality of two MarkCommands.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand otherCommand = (MarkCommand) o;
        return this.indices.equals(otherCommand.indices);
    }
}

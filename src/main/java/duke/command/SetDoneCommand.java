package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class SetDoneCommand extends Command {
    private final int index;
    private final boolean isDone;

    /**
     * Initialises a {@code SetDoneCommand} with an {@code int} index and {@code boolean} isDone.
     *
     * @param index The index of the {@code Task} to be marked as done/undone.
     * @param isDone Whether the {@code Task} is to be marked as done or undone.
     */
    public SetDoneCommand(int index, boolean isDone) {
        assert index >= 0;
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.setDone(index, isDone);
        try {
            Storage.write(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return String.format("Nice! I've marked this task as done:\n %s",
                tasks.get(index));
    }
}

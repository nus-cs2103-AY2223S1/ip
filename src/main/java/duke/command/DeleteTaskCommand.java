package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * Initialises a {@code DeleteTaskCommand} with an {@code int} index.
     *
     * @param index The index of the {@code Task} to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) {
        Task task = tasks.remove(index);
        try {
            Storage.write(tasks);
        } catch (DukeException e) {
            Parser.printMsg(e.getMessage());
        }
        Parser.printMsg(String.format("Noted. I've removed this task:\n %s",
                task));
    }
}

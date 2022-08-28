package duke;

import java.io.IOException;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

package duke;

import java.io.IOException;

/**
 * Marks a task as not done.
 */
public class UndoneCommand extends Command {
    private int index;
    public UndoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.unmark(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return msg;
    }
}

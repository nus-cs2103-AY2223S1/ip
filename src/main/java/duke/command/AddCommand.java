package duke.command;

import duke.Storage;
import duke.models.Task;

import java.util.List;

public class AddCommand extends Command {
    private final Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public void execute(List<Task> list, Storage storage) {

    }
}

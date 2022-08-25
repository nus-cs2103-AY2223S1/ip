package duke.command;

import duke.Storage;
import duke.models.Task;

import java.util.List;

public abstract class Command {

    public abstract void execute(List<Task> list, Storage storage);
}

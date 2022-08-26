package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;

public interface Command {
    public void execute(TaskList tasks, Storage storage) throws DukeException;
}

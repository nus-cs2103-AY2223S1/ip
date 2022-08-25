package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.list(tasks.getTasks());
    }
}

package duke.command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
public class ListCommand extends Command{
    public ListCommand() {
        super();
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

}

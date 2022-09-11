package Command;
import Duke.DukeUi;
import Duke.Storage;
import Duke.TaskList;
/**
* Command that prints out the list of user's tasks when executed.
*/
public class ListCommand extends Command {

    boolean isExit;

    public ListCommand() {
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) {
        return tasks.toString();
    }

    @Override
    public String toString() {
        return "this is a list command";
    }
}

package command;
import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanUi;
/**
* Command that prints out the list of user's tasks when executed.
*/
public class ListCommand extends Command {

    private boolean isExit;

    /**
     * Constructor for a list command.
     */
    public ListCommand() {
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) {
        return tasks.toString();
    }

    @Override
    public String toString() {
        return "this is a list command";
    }
}

/**
 * Handles a to-do command
 */
public class ListCommand extends Command {

    boolean exit;

    public ListCommand() {
        this.exit = false;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) {
        ui.sendMessage(tasks.toString());
    }

}

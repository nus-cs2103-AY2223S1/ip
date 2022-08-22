package duke;

public class FindCommand extends Command {

    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks, filter);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

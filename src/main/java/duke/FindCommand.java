package duke;

public class FindCommand extends Command {

    String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks, filter);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

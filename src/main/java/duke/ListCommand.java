package duke;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}

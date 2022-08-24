public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}

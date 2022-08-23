public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        ui.showList(list);
    }
}

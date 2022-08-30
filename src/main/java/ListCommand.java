public class ListCommand extends Command{

    public ListCommand(){}

    @Override
    void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.listAll();
    }
}

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.deleteTask(this.index, storage);
    }
}

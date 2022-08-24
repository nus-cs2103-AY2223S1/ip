public class EndCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList);
        return;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}

public class ByeCommand extends Command{

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.GoodBye();
    }
}

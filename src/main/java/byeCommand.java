public class byeCommand extends Command{

    @Override
    public void execute(TaskList tasks) {
        Ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
